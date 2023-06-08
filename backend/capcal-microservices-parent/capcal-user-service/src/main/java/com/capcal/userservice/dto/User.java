package com.capcal.userservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class User {

    @Id
    @Column(name="username")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name="password",nullable = false)
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email",unique = true)
    private String email;

    @Column(name="callimit")
    private Integer callimit;

    @NotBlank(message = "Role is mandatory")
    @Column(name="role",nullable = false)
    private String role;



}
