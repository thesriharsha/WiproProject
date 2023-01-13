package com.stackroute.wishlistms.service;

import com.stackroute.wishlistms.dto.AddToWatchList;

import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface IWishListService {

    WishListCityDeatils addToWishList(@Valid AddToWatchList requestData) throws  CityInfoAlreadyExistsException;

    void remove(@Valid RemoveFromWatchList requestData) throws CityInfoNotFoundException;

    List<WishListCityDeatils> listWatchListByUserName( @NotBlank String userId) throws CityInfoNotFoundException;


}
