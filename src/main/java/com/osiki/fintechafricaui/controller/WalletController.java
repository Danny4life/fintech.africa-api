package com.osiki.fintechafricaui.controller;

import com.osiki.fintechafricaui.model.WalletModel;
import com.osiki.fintechafricaui.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WalletController {

    @Autowired
    private WalletService walletService;


    @PostMapping("/viewWallet")

    public ResponseEntity<WalletModel> viewWallet(){
        WalletModel walletModel = walletService.viewWalletDetails();

        return new ResponseEntity<>(walletModel, HttpStatus.OK);
    }
}
