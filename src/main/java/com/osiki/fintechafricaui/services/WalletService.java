package com.osiki.fintechafricaui.services;

import com.osiki.fintechafricaui.exception.UserNotFoundException;
import com.osiki.fintechafricaui.model.WalletModel;

public interface WalletService {
    WalletModel viewWalletDetails() throws UserNotFoundException;
}
