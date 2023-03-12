package com.osiki.fintechafricaui.controller;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.event.RegistrationCompleteEvent;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
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
                application(request)
        ));

        return "Registration Successful";

    }

    private String application(HttpServletRequest request) {

        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
