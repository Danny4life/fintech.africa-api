package com.osiki.fintechafricaui.event.listener;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        // create the verification token for the user with link

        Users user = event.getUser();

        String token = UUID.randomUUID().toString();

        //send mail to user

    }
}
