package com.osiki.fintechafricaui.services.serviceImpl;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.entity.Wallet;
import com.osiki.fintechafricaui.exception.UserNotFoundException;
import com.osiki.fintechafricaui.model.WalletModel;
import com.osiki.fintechafricaui.respository.UsersRepository;
import com.osiki.fintechafricaui.respository.WalletRepository;
import com.osiki.fintechafricaui.services.WalletService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public WalletModel viewWalletDetails() throws UserNotFoundException {
        WalletModel walletModel = new WalletModel();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = usersRepository.findByEmail(user.getUsername());


        Wallet wallet = users.getWallet();
        walletModel.setBalance(walletModel.getBalance());
        walletModel.setBankName(wallet.getBankName());
        walletModel.setAccountNumber(wallet.getAccountNumber());
        BeanUtils.copyProperties(users, walletModel);

        return walletModel;
    }
}
