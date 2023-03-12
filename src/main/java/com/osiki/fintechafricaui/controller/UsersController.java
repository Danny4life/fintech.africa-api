package com.osiki.fintechafricaui.controller;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.entity.VerificationToken;
import com.osiki.fintechafricaui.event.RegistrationCompleteEvent;
import com.osiki.fintechafricaui.model.PasswordModel;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String createUsersAccount(@RequestBody UsersModel usersModel, final HttpServletRequest request){

        Users user = usersService.createUsersAccount(usersModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));

        return "Registration Successful";

    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){

        String result = usersService.validateVerificationToken(token);

        if(result.equalsIgnoreCase("valid")){
            return "User verifies successfully";
        }

        return "Bad User";

    }

    @GetMapping("/resendVerifyToken")

    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request){

        VerificationToken verificationToken =
                usersService.generateNewVerificationToken(oldToken);

        Users user = verificationToken.getUser();

        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);

        return "Verification link sent";

    }
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){

        Users user = usersService.findUserByEmail(passwordModel.getEmail());

        String url = "";

        if(user != null){
            String token = UUID.randomUUID().toString();
            usersService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }

        return url;

    }

    private String passwordResetTokenMail(Users user, String applicationUrl, String token) {

        String url =
                applicationUrl
                        + "/savePassword?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to reset your password: {}",
                url);

        return url;
    }

    //take this to event package
    public void resendVerificationTokenMail(Users user, String applicationUrl, VerificationToken verificationToken){
        String url =
                applicationUrl
                        + "/verifyRegistration?token=" // change link to resendVerifyToken
                        + verificationToken.getToken();

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
    }

    private String applicationUrl(HttpServletRequest request) {

        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
