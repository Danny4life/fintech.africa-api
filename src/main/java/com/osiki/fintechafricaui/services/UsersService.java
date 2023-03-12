package com.osiki.fintechafricaui.services;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.model.UsersModel;

public interface UsersService {
    Users createUsersAccount(UsersModel usersModel);

    void saveVerificationTokenForUser(String token, Users user);

    String validateVerificationToken(String token);
}
