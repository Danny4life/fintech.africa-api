package com.osiki.fintechafricaui.controller;

import com.osiki.fintechafricaui.entity.Users;
import com.osiki.fintechafricaui.model.UsersModel;
import com.osiki.fintechafricaui.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    public String createUsersAccount(@RequestBody UsersModel usersModel){

        Users user = usersService.createUsersAccount(usersModel);

    }
}
