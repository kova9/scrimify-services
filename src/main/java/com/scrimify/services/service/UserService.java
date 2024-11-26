package com.scrimify.services.service;

import com.scrimify.services.enums.UserRole;
import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.LoginRequest;
import com.scrimify.services.model.request.UserRoleRequest;
import com.scrimify.services.model.Users;
import com.scrimify.services.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;

    @Autowired
    private ApplicationContext context;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(generateId());
        user.getRoles().add(UserRole.ROLE_USER.getCode());

        repo.save(user);
        return user;
    }

    public String verify(LoginRequest user) {
        try{
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            Users authenticatedUser = ((UserPrincipal) authentication.getPrincipal()).getUser();
            return jwtService.generateToken(authenticatedUser);
        }catch (AuthenticationException e){
            System.out.println(e);
            throw ScrimifyException.unauthorized("Wrong Data");
        }
    }

    public Users changeUserRole(UserRoleRequest request){
        Users user = repo.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(request.getUserId() + " nix gefunden"));
        user.getRoles().add(request.getRole());
        repo.save(user);

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
}
