package com.example.bankoperationservice.model.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.bankoperationservice.model.role.Permissions.BASIC_USER_ACCESS;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(Set.of(BASIC_USER_ACCESS));
    private final Set<Permissions> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

