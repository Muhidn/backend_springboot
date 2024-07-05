package com.Garbage.projectColection.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "phoneNumber" , nullable = false)
    private String phoneNumber;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "role" , nullable = false)
    private String role;
}
