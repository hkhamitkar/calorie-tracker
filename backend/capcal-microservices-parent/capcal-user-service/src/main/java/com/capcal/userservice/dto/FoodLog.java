package com.capcal.userservice.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="foodlog")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FoodLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "food_name")
    private String foodName;

    @Column(name="food_date",nullable = false)
    private Date intakeDate;

    @Column(name="calories",nullable = false)
    private int calories;

    @Column(name="username" , nullable = false)
    private String username;


}
