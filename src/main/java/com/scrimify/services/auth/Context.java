package com.scrimify.services.auth;


import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER) // Annotation can only be used on method parameters
@Retention(RetentionPolicy.RUNTIME) // Annotation is available at runtime
@Documented
@AuthenticationPrincipal
public @interface Context {
}
