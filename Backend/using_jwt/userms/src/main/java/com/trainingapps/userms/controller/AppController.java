package com.trainingapps.userms.controller;

import com.trainingapps.userms.dto.LoginUserRequest;
import com.trainingapps.userms.dto.RegisterRequestDto;
import com.trainingapps.userms.dto.UserDetails;
import com.trainingapps.userms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin("*")
@RestController
public class AppController {

    @Autowired
    private IUserService service;

    @PostMapping("/register")
    public UserDetails register(@RequestBody RegisterRequestDto requestData) {
        System.out.println("**inside register");
        UserDetails response = service.register(requestData);
        return response;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserRequest request) throws Exception {
        System.out.println("**inside login");
        String token = service.login(request);
        return token;
    }
}
