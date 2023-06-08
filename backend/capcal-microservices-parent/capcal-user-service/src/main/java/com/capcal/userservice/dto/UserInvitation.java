package com.capcal.userservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
public class UserInvitation extends User{
    private String senderEmail;
    private String senderName;
    private String token;
}
