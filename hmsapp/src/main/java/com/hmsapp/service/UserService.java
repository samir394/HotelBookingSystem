package com.hmsapp.service;

import com.hmsapp.entity.User;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTservice jwtService; // Inject JWTservice

    // Constructor injection for both userRepository and jwtService
    public UserService(UserRepository userRepository, JWTservice jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()) {
            User user = opUser.get();
            // Removed the extraneous semicolon and fixed the block
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                // Generate token only if the password matches
                String token = jwtService.generateToken(user.getUsername());
                return token;
            } else {
                // Password mismatch
                return null;
            }
        } else {
            // User not found
            return null;
        }
    }
}
