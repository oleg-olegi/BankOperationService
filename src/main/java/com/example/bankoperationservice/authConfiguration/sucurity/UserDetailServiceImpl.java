package com.example.bankoperationservice.authConfiguration.sucurity;

import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserData user = userRepository.findUsersByLoginIgnoreCase(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username '%s' not found", login)));
        return SecurityUser.fromUserData(user);
    }
}
