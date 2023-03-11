package com.osiki.fintechafricaui.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tbl")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 11)
    private String phoneNumber;
    @Column(length = 60)
    private String password;
    @Column(length = 11)
    private String bvn;
    private String pin;
    private String role;
    private boolean enabled = false;
}
