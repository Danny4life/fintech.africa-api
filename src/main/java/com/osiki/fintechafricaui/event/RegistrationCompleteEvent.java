package com.osiki.fintechafricaui.event;

import com.osiki.fintechafricaui.entity.Users;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {

    private Users user;
    private String applicationUrl;

    public RegistrationCompleteEvent(Users user, String applicationUrl ) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
