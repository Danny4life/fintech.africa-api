package com.osiki.fintechafricaui.services.serviceImpl;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.entity.VerificationToken;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.respository.UsersRepository;
import com.osiki.fintechafricaui.respository.VerificationTokenRepository;
import com.osiki.fintechafricaui.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
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
        user.setPassword(passwordEncoder.encode(usersModel.getPassword()));

        usersRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, Users user) {

        VerificationToken verificationToken = new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken);

    }
}
