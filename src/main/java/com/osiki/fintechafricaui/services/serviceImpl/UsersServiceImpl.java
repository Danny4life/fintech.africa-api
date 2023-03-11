package com.osiki.fintechafricaui.services.serviceImpl;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.services.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Override
    public Users createUsersAccount(UsersModel usersModel) {

        Users user = new Users();

        user.setFirstName(usersModel.getFirstName());
        user.setLastName(usersModel.getLastName());
        user.setEmail(usersModel.getEmail());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setBvn(usersModel.getBvn());
        user.setPin(user.getPin());
        user.setRole("User");
        user.setPassword(usersModel.getPassword());
        return null;
    }
}
