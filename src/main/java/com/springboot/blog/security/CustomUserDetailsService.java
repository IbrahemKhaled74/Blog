package com.springboot.blog.security;

import com.springboot.blog.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    //save user in data base
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        com.springboot.blog.model.UserDetails user = userRepo.findByEmailOrUsername(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("UserName Not Found "));
        Set<GrantedAuthority> authorities= user.getRole().stream()
                .map((role -> new SimpleGrantedAuthority(role.getRole().name()))).collect(Collectors.toSet());

        return new User(user.getEmail(),user.getPassword(),authorities);

    }
}
