package com.capcal.userservice.controller;

import com.capcal.userservice.common.ParameterConstants;
import com.capcal.userservice.dto.AuthRequest;
import com.capcal.userservice.dto.User;
import com.capcal.userservice.dto.UserInvitation;
import com.capcal.userservice.service.JwtService;
import com.capcal.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.principal.username OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserData(@PathVariable String username){
        try {
            return new ResponseEntity<User>(userService.getUserData(username), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User not found",e);
        }
    }

    @PostMapping("/adduser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity addUser(@Validated @RequestBody User user){
        try {
            try {
                if (null != userService.getUserData(user.getName())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "User already exists");
                    //return new ResponseEntity("User Already Exists",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (NoSuchElementException e) {
                log.error("User not present" + user);
            }

            user.setPassword(passwordEncoder.encode(ParameterConstants.generateRandomPassword(8)));
            userService.addUser(user);
        } catch (JpaSystemException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "DB error, please check server logs", e);
        }
       return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity sendEmailInvitation(@RequestBody UserInvitation invitation){

        try {
            User user=new User(invitation.getName(),invitation.getPassword(),invitation.getEmail(),2100,"ROLE_USER");
            try {
                if (null != userService.getUserData(user.getName())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "User already exists");
                    //return new ResponseEntity("User Already Exists",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (NoSuchElementException e) {
                log.error("User not present" + user);
            }

            String password=ParameterConstants.generateRandomPassword(8);
            user.setPassword(passwordEncoder.encode(password));
            userService.addUser(user);
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(user.getName());
            authRequest.setPassword(password);
            String token = this.authenticateAndGetToken(authRequest);
            invitation.setToken(token);
            userService.sendEmailInvitation(invitation);
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Failed to send invitataion"+ ex);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }
}
