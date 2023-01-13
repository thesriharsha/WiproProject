package com.trainingapps.userms.dao;

import com.trainingapps.userms.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser>findByUsername(String username);

}
