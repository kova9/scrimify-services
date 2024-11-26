package com.scrimify.services.util;

import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.Users;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static Users getCurrenUser(){
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
