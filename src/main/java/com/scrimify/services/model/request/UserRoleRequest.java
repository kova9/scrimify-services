package com.scrimify.services.model.request;

import lombok.Data;

@Data
public class UserRoleRequest {
    private String userId;
    private String role;
}
