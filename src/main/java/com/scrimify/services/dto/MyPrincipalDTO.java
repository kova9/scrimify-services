package com.scrimify.services.dto;

import com.scrimify.services.model.GameAccount;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class MyPrincipalDTO {
    private String id;
    private String username;
    private String email;
    private String countryCode;
    private List<GameAccount> gameAccounts;
    private Collection<? extends GrantedAuthority> roles;
}
