package com.scrimify.services.contoller;

import com.scrimify.services.auth.Context;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.Users;
import com.scrimify.services.model.request.LoginRequest;
import com.scrimify.services.model.request.RegisterRequest;
import com.scrimify.services.model.request.UserRoleRequest;
import com.scrimify.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest req) {
        return service.register(req);

    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user) {

        return service.verify(user);
    }

    @PostMapping("/userRole")
    public Users changeUserRole(@RequestBody UserRoleRequest user, @AuthenticationPrincipal UserPrincipal context){
        return service.changeUserRole(user, context);
    }

}
