package com.osiki.fintechafricaui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet_tbl")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private Double balance = 0.00;
    @Column(length = 10, nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String bankName;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}
