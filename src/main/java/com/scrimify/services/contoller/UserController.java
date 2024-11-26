package com.scrimify.services.contoller;

import com.scrimify.services.model.Users;
import com.scrimify.services.model.request.LoginRequest;
import com.scrimify.services.model.request.UserRoleRequest;
import com.scrimify.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user) {

        return service.verify(user);
    }

    @PostMapping("/userRole")
    public Users changeUserRole(@RequestBody UserRoleRequest user){
        return service.changeUserRole(user);
    }

}
