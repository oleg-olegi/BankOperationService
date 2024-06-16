package com.example.bankoperationservice.model.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permissions {
    BASIC_USER_ACCESS("basic_user_access");
    private final String permission;
}
