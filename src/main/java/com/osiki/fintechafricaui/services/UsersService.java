package com.osiki.fintechafricaui.services;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.entity.VerificationToken;
import com.osiki.fintechafricaui.model.LoginModel;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.response.LoginResponse;

import java.util.Optional;

public interface UsersService {
    Users createUsersAccount(UsersModel usersModel);

    void saveVerificationTokenForUser(String token, Users user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    Users findUserByEmail(String email);

    void createPasswordResetTokenForUser(Users user, String token);

    String validatePasswordResetToken(String token);

    Optional<Users> getUserByPasswordResetToken(String token);

    void changePassword(Users users, String newPassword);

    boolean checkIfValidOldPassword(Users user, String oldPassword);

    LoginResponse loginUser(LoginModel loginModel);

    UsersModel updateUser(Long id, UsersModel usersModel);
}
