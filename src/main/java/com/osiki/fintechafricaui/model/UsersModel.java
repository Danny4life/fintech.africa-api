package com.osiki.fintechafricaui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 11)
    private String phoneNumber;
    @Column(length = 60)
    private String password;
    private String confirmPassword;
    @Column(length = 11)
    private String bvn;
    private String pin;
}
