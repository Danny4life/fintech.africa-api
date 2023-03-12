package com.osiki.fintechafricaui.event.listener;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.event.RegistrationCompleteEvent;
import com.osiki.fintechafricaui.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UsersService usersService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        // create the verification token for the user with link

        Users user = event.getUser();

        String token = UUID.randomUUID().toString();

        usersService.saveVerificationTokenForUser(token, user);

        //send mail to user

        String url = event.getApplicationUrl()
                +  "/verifyRegistration?token="
                + token;

        // this is where to send verification email
        log.info("Click the link to verify your account: {}", url);

    }
}
