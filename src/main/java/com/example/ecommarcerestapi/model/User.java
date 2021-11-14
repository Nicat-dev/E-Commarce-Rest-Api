package com.example.ecommarcerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;
    @Column(name = "user_first_name",nullable = false)
    private String userFirstName;
    @Column(name = "user_surname",nullable = false)
    private String userSurname;
    @Column(name = "user_email",nullable = false)
    private String userEmail;
    @Column(name = "user_adderss",nullable = false)
    private String userAdress;
    @Column(name = "userPassword",nullable = false)
    private String userPassword;
    @Column(name = "user_age",nullable = false)
    private Integer userAge;
    @Column(name = "user_gender",nullable = false)
    private Integer userGeneder;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "status", columnDefinition = "integer default 1")
    private Integer status;
}
