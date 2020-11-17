package com.tmp.BTS.security;

import com.tmp.BTS.security.model.SecurityUser;
import com.tmp.BTS.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {

    private UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(u -> new SecurityUser(u.toAuthUser(), Collections.singleton(new SimpleGrantedAuthority(u.getRole()))))
                .orElseThrow(()->new UsernameNotFoundException(email));
    }
}