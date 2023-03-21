package com.osiki.fintechafricaui.services.serviceImpl;

import com.osiki.fintechafricaui.model.WalletModel;
import com.osiki.fintechafricaui.respository.UsersRepository;
import com.osiki.fintechafricaui.respository.WalletRepository;
import com.osiki.fintechafricaui.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public WalletModel viewWalletDetails() {
        WalletModel walletModel = new WalletModel();
        return null;
    }
}
