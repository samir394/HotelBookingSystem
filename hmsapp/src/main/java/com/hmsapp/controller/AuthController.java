package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.JwtToken;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.ProfileDto;
import com.hmsapp.repository.UserRepository;
import com.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@RestController
@RequestMapping("/api/auths")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //User for user sign-up+login
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Check if the username exists
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        // Check if the email exists
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Check if the mobile number exists
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.BAD_REQUEST);
        }

        // Encrypt the password before saving
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER");
        // Save the new user
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwnerAccount(
            @RequestBody User user
    ) {
        // Check if the username exists
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String token = userService.verifyLogin(loginDto);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setType("JWT");
        if (token != null) {
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);

        }
        return new ResponseEntity<>("invalid", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    public ResponseEntity<ProfileDto>getUserProfile(

            @AuthenticationPrincipal User user
    ){
        ProfileDto dto =new ProfileDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
