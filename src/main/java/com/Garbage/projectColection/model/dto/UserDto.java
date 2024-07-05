package com.Garbage.projectColection.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userID;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;

}
