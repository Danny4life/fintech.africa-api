package com.osiki.fintechafricaui.event;

import com.osiki.fintechafricaui.entity.Users;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {

    private Users users;
    private String applicationUrl;

    public RegistrationCompleteEvent(Users users, String applicationUrl ) {
        super(users);
        this.users = users;
        this.applicationUrl = applicationUrl;
    }
}
