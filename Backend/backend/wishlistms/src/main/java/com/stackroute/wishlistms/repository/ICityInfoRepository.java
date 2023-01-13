package com.stackroute.wishlistms.repository;

import java.util.List;
import com.stackroute.wishlistms.entity.CityInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICityInfoRepository extends MongoRepository<CityInfo,String> {

    List<CityInfo> findByUserName(String userName);
    Optional<CityInfo> findByUserNameAndCityAndStateAndCountry(String userName, String city, String state, String country);
}
