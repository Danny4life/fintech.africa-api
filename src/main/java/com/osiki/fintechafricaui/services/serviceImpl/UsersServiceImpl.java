package com.osiki.fintechafricaui.services.serviceImpl;

import com.osiki.fintechafricaui.entity.PasswordResetToken;
import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.entity.VerificationToken;
import com.osiki.fintechafricaui.exception.EmailAlreadyTakenException;
import com.osiki.fintechafricaui.model.LoginModel;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.response.LoginResponse;
import com.osiki.fintechafricaui.respository.PasswordResetTokenRepository;
import com.osiki.fintechafricaui.respository.UsersRepository;
import com.osiki.fintechafricaui.respository.VerificationTokenRepository;
import com.osiki.fintechafricaui.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public Users createUsersAccount(UsersModel usersModel) {

        Users user = new Users();
        user.setFirstName(usersModel.getFirstName());
        user.setLastName(usersModel.getLastName());
        user.setEmail(usersModel.getEmail());
        user.setPhoneNumber(usersModel.getPhoneNumber());
        user.setBvn(usersModel.getBvn());
        user.setPin(usersModel.getPin());
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

    @Override
    public String validateVerificationToken(String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null){
            return "invalid";
        }

        // get the user verification token
        Users user = verificationToken.getUser();

        Calendar cal = Calendar.getInstance();
        // delete token if expires
        if((verificationToken.getExpirationTime().getTime()
        - cal.getTime().getTime()) <= 0 ){
            verificationTokenRepository.delete(verificationToken);

            return "expired";
        }

        // enable the user if verification is valid
        user.setEnabled(true);
        usersRepository.save(user);

        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {

        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(oldToken);

        verificationToken.setToken(UUID.randomUUID().toString());

        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(Users user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);

        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {

        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken == null){
            return "invalid";
        }

        Users user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {

            passwordResetTokenRepository.delete(passwordResetToken);

            return "expired";
        }
        return "valid";

    }

    @Override
    public Optional<Users> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(Users users, String newPassword) {
        users.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(users);
    }

    @Override
    public boolean checkIfValidOldPassword(Users user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public LoginResponse loginUser(LoginModel loginModel) {

        String msg = "";
        Users user1 = usersRepository.findByEmail(loginModel.getEmail());

        if(user1 != null){
            String password = loginModel.getPassword();
            String encondedPassword = user1.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encondedPassword);

            if(isPasswordRight){
                Optional<Users> user =
                        usersRepository.findOneByEmailOrPassword(loginModel.getEmail(), encondedPassword);

                if(user.isPresent()){

                    return new LoginResponse("Login successful", true);

                }else {
                    return new LoginResponse("Login failed", false);
                }
            }else {
                return new LoginResponse("Email or password not match", false);
            }
        } else {
            return new LoginResponse("Email does not exists", false);
        }

    }
}
