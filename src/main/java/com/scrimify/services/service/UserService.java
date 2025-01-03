package com.scrimify.services.service;

import com.scrimify.services.dto.MyPrincipalDTO;
import com.scrimify.services.enums.UserRole;
import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.model.GameAccount;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.Users;
import com.scrimify.services.model.request.LoginRequest;
import com.scrimify.services.model.request.RegisterRequest;
import com.scrimify.services.model.request.UserRoleRequest;
import com.scrimify.services.repo.GameAccountRepo;
import com.scrimify.services.repo.UserRepo;
import com.scrimify.services.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GameAccountRepo gameAccountRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(RegisterRequest req) {
        Users user = new Users();
        userRepo.findByUsername(req.getUsername()).ifPresent(thisUser -> {
                    throw ScrimifyException.badRequest("Username already taken");
                });

        userRepo.findByEmail(req.getEmail()).ifPresent(thisUser -> {
            throw ScrimifyException.badRequest("E-Mail already taken");
        });

        user.setPassword(encoder.encode(req.getPassword()));
        user.setId(generateId());
        user.getRoles().add(UserRole.ROLE_USER.getCode());
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setDob(req.getDob());
        user.setCountryCode(req.getCountryCode());
        user.setCreateTimestamp(TimestampUtil.now());

        userRepo.save(user);
        return user;
    }

    public String verify(LoginRequest user) {
        try{
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            Users authenticatedUser = ((UserPrincipal) authentication.getPrincipal()).getUser();
            return jwtService.generateToken(authenticatedUser);
        }catch (AuthenticationException e){
            throw ScrimifyException.unauthorized("Wrong Data");
        }
    }

    public Users changeUserRole(UserRoleRequest request, UserPrincipal context){
        if (context.getUser().getRoles().stream().noneMatch(role -> role.equals(UserRole.ROLE_ADMIN.getCode()))) {
            throw ScrimifyException.unauthorized("User rights not enough");
        }

        Users user = userRepo.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(request.getUserId() + " nix gefunden"));
        user.getRoles().add(request.getRole());
        userRepo.save(user);

        return user;
    }

    public static String generateId() {
        String PREFIX = "SFY___";
        String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder idBuilder = new StringBuilder(PREFIX);
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            idBuilder.append(CHAR_POOL.charAt(index));
        }

        return idBuilder.toString();
    }

    public MyPrincipalDTO myPrincipal(UserPrincipal userPrincipal) {
        List<GameAccount> gameAccounts = gameAccountRepo.findByUserId(userPrincipal.getUserId()).orElse(Collections.emptyList());
        MyPrincipalDTO dto = new MyPrincipalDTO();
        dto.setId(userPrincipal.getUserId());
        dto.setUsername(userPrincipal.getUsername());
        dto.setEmail(userPrincipal.getEmail());
        dto.setCountryCode(userPrincipal.getUser().getCountryCode());
        dto.setGameAccounts(gameAccounts);
        dto.setRoles(userPrincipal.getAuthorities());

        return dto;
    }
}
