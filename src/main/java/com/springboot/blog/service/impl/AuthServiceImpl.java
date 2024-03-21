package com.springboot.blog.service.impl;

import com.springboot.blog.dto.LoginDto;
import com.springboot.blog.dto.RegisterDto;
import com.springboot.blog.exception.BlogException;
import com.springboot.blog.model.Role;
import com.springboot.blog.model.UserDetails;
import com.springboot.blog.repository.RoleRepo;
import com.springboot.blog.repository.UserRepo;
import com.springboot.blog.service.AuthService;
import com.springboot.blog.utils.RoleName;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepo userRepo, RoleRepo roleRepo) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return "Login Successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername())){
            throw  new BlogException(HttpStatus.BAD_REQUEST,"This user name already exist");
        }
        if (userRepo.existsByEmail(registerDto.getEmail())){
            throw  new BlogException(HttpStatus.BAD_REQUEST,"This email already exist");
        }
        Set<Role>roleNames=new HashSet<>();
        roleNames.add(roleRepo.findByRole(RoleName.USER).get());
        UserDetails user = UserDetails.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .name(registerDto.getName())
                .role(roleNames)
                .build();
        userRepo.save(user);


        return "The email Save Successfully";
    }
}
