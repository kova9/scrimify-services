package com.scrimify.services.service;

import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.Users;
import com.scrimify.services.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username).orElseThrow(() -> ScrimifyException.notFound("User not found"));

        return new UserPrincipal(user);
    }
}
