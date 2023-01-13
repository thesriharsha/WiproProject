package com.stackroute.wishlistms.controller;

import com.stackroute.wishlistms.dto.AddToWatchList;

import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;


import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import com.stackroute.wishlistms.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/wishlist")
@RestController
public class WishListRestApi {

    @Autowired
    private IWishListService service;


    @GetMapping("/byid/{id}")

    public List<WishListCityDeatils> findAll(@PathVariable  String id) throws Exception {

        List<WishListCityDeatils> response = service.listWatchListByUserName(id);
        return response;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public WishListCityDeatils add(@RequestBody AddToWatchList requestData) throws Exception {
        WishListCityDeatils response= service.addToWishList(requestData);
        return response;
    }

    @DeleteMapping("/delete")
    public void remove(@RequestBody RemoveFromWatchList requestData) throws CityInfoNotFoundException {

       service.remove(requestData);

    }

}
