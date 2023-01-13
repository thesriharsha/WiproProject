package com.stackroute.wishlistms.service;

import com.stackroute.wishlistms.repository.ICityInfoRepository;

import com.stackroute.wishlistms.dto.AddToWatchList;

import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.entity.CityInfo;
import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;

import com.stackroute.wishlistms.util.WishlistCityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WishListServiceImpl implements IWishListService {

    @Autowired
    private ICityInfoRepository repository;


    @Autowired
    private WishlistCityUtil util;
    //


    //
//
    public String generateId(String userID, String city, String state, String country) {
      String id = city+"-"+state+"-"+country+"-u-"+userID;
      return id;
    }

    @Override
    public WishListCityDeatils addToWishList(AddToWatchList requestData) throws CityInfoAlreadyExistsException {
        Optional<CityInfo> optional = repository.findByUserNameAndCityAndStateAndCountry(requestData.getUserName(), requestData.getCity(), requestData.getState(), requestData.getCountry());
        if (optional.isPresent()) {
            throw new CityInfoAlreadyExistsException("City Info is already present in the WishList!");

        }
        CityInfo cityInfo;
        cityInfo = util.toCityInfo(requestData);
        cityInfo.setId(generateId(requestData.getUserName(),requestData.getCity(),requestData.getState(),requestData.getCountry()));

        cityInfo = repository.save(cityInfo);

        WishListCityDeatils desired = util.toCityDetails(cityInfo);
        return desired;
    }

    @Override
    public void remove(RemoveFromWatchList requestData) throws CityInfoNotFoundException {

        Optional<CityInfo> optional = repository.findByUserNameAndCityAndStateAndCountry(requestData.getUserName(), requestData.getCity(), requestData.getState(), requestData.getCountry());
        if (!optional.isPresent()) {
            throw new CityInfoNotFoundException("No City Found!");
        }
        CityInfo cityInfo = optional.get();
        repository.delete(cityInfo);

    }

    @Override
    public List<WishListCityDeatils> listWatchListByUserName(String userId) throws CityInfoNotFoundException {
        List<CityInfo> cityInfoList = repository.findByUserName(userId);
        if (cityInfoList.isEmpty()) {
            throw new CityInfoNotFoundException("No City Found!");
        }
        List<WishListCityDeatils> desired = util.toListCityDetails(cityInfoList);
        return desired;
    }
}

