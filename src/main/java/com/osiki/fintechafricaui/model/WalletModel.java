package com.osiki.fintechafricaui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletModel {

    private String firstName;
    private String lastName;
    private Double balance;
    private String accountNumber;
    private String bankName;
}
