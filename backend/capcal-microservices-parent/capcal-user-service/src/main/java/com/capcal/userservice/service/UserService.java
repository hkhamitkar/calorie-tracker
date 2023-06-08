package com.capcal.userservice.service;

import com.capcal.userservice.dto.User;
import com.capcal.userservice.dto.UserInvitation;
import com.capcal.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    JwtService jwtService;

    public User getUserData(final String username){
        return userRepository.findById(username).orElseThrow(NoSuchElementException::new);
    }

    public void addUser(final User user){
        userRepository.save(user);
    }

    public void sendEmailInvitation(UserInvitation invitation) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(invitation.getSenderName()+"<"+invitation.getSenderEmail()+">");
        message.setTo(invitation.getEmail());
        message.setSubject("Capcal Invitation from  "+ invitation.getSenderName());
        StringBuilder messageContent=new StringBuilder();
        message.setText("http://localhost:3030/user/"+invitation.getName()+"?token="+invitation.getToken());
        mailSender.send(message);
    }
}
