package com.trainingapps.userms.service;

import com.trainingapps.userms.dto.LoginUserRequest;
import com.trainingapps.userms.dto.RegisterRequestDto;
import com.trainingapps.userms.dto.UserDetails;
import com.trainingapps.userms.entity.AppUser;
import com.trainingapps.userms.exceptions.IncorrectCredentialsException;
import com.trainingapps.userms.exceptions.InvalidTokenException;
import com.trainingapps.userms.exceptions.UserNotFoundException;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface IUserService {

    AppUser findByUsername(@NotBlank @Length(min = 2, max = 15) String username) throws UserNotFoundException;

    UserDetails findUserDetailsByUsername(@NotBlank @Length(min = 2, max = 15) String username) throws UserNotFoundException;

    UserDetails register(@Valid RegisterRequestDto request);

    UserDetails authenticateByToken(String token)throws InvalidTokenException,UserNotFoundException ;

    String login(LoginUserRequest request) throws IncorrectCredentialsException;

    List<UserDetails>findAll();
}
