package com.trainingapps.userms.service;

import com.trainingapps.userms.dao.IUserRepository;
import com.trainingapps.userms.dto.LoginUserRequest;
import com.trainingapps.userms.dto.RegisterRequestDto;
import com.trainingapps.userms.dto.UserDetails;
import com.trainingapps.userms.entity.AppUser;
import com.trainingapps.userms.exceptions.IncorrectCredentialsException;
import com.trainingapps.userms.exceptions.InvalidTokenException;
import com.trainingapps.userms.exceptions.UserNotFoundException;
import com.trainingapps.userms.util.TokenUtil;
import com.trainingapps.userms.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public AppUser findByUsername(String username) throws UserNotFoundException {
        Optional<AppUser> optional = userRepository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("user not found for username=" + username);
        }
        AppUser user = optional.get();
        return user;
    }

    @Override
    public UserDetails findUserDetailsByUsername(String username) throws UserNotFoundException {
        AppUser user = findByUsername(username);
        UserDetails desired = userUtil.toUserDetails(user);
        return desired;
    }

    @Override
    public UserDetails register(RegisterRequestDto request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user = userRepository.save(user);
        UserDetails desired = userUtil.toUserDetails(user);
        return desired;
    }

    @Override
    public UserDetails authenticateByToken(String token) throws InvalidTokenException, UserNotFoundException {
        String username = tokenUtil.decodeToken(token);
        AppUser user = findByUsername(username);
        UserDetails desired = userUtil.toUserDetails(user);
        return desired;
    }

    @Override
    public String login(LoginUserRequest request) throws IncorrectCredentialsException {
        Optional<AppUser> optional= userRepository.findByUsername(request.getUsername());
        if(!optional.isPresent()){
            throw new IncorrectCredentialsException("incorrect credentials");
        }
        AppUser user=optional.get();
        if(!user.getPassword().equals(request.getPassword())){
            throw new IncorrectCredentialsException("incorrect credentials");
        }
        String token = tokenUtil.generateToken(request.getUsername());
        return token;
    }

    @Override
    public List<UserDetails> findAll() {
        List<AppUser>users= userRepository.findAll();
        List<UserDetails>desired=userUtil.toUserDetailsList(users);
        return desired;
    }
}
