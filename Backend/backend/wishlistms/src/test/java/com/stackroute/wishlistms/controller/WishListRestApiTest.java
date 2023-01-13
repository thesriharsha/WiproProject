package com.stackroute.wishlistms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.wishlistms.dto.AddToWatchList;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.entity.Location;
import com.stackroute.wishlistms.entity.Pollution;
import com.stackroute.wishlistms.entity.Weather;
import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import com.stackroute.wishlistms.service.IWishListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(WishListRestApi.class)
class WishListRestApiTest {

    private WishListCityDeatils response;

    @MockBean
    IWishListService service;

    @Autowired
    MockMvc mvc;


    @BeforeEach
    public void setUp() {
        response = new WishListCityDeatils();
        response.setCity("delhi");
        response.setState("delhi");
        response.setCountry("india");
        Weather weather = new Weather();
        Location location = new Location();
        Pollution pollution = new Pollution();
        location.setType("point");
        location.setCoordinates(null);
        pollution.setTime(LocalDate.now().toString());
        pollution.setAqius(27);
        weather.setHumidity(27);
        weather.setPressure(14);
        weather.setTemperature(10);
        weather.setWind(4);
        response.setWeather(weather);
        response.setLocation(location);
        response.setPollution(pollution);
    }

    @AfterEach
    public void tearDown() {
        response = null;
    }

    /**
     * scenario: When list is fetched successfully
     * input : userName=1
     * expectation: List is fetched successfully. status 200/OK
     */
    @Test
    public void testFindAll_1() throws Exception {
        String userName = "1";
        List<WishListCityDeatils> Citys = new ArrayList<>();
        Citys.add(response);
        when(service.listWatchListByUserName(userName)).thenReturn(Citys);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Citys);
        System.out.println("**created json=" + json);
        String url = "/wishlist/byid/" + userName;
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(json));


    }

    /**
     * scenario: No City is found in the Wish list
     * input : userName: 1
     * expectation: CityNotFoundException is thrown. status 404/NOT_FOUND
     */
    @Test
    public void testFindAll_2() throws Exception {
        String userName = "1";
        String msg = "No City found";
        CityInfoNotFoundException e = new CityInfoNotFoundException(msg);
        when(service.listWatchListByUserName(userName)).thenThrow(e);
        String url = "/wishlist/byid/" + userName;
        mvc.perform(get(url))
                .andExpect(status().isNotFound())
                .andExpect(content().string(msg));

    }

    /**
     * scenario: When City is added successfully
     * input : AddToWatchList request
     * expectation:  City is added successfully. status 201/OK
     */
    @Test
    public void testAdd_1() throws Exception {

        AddToWatchList request = new AddToWatchList();
        request.setUserName("1");
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");
        Weather weather = new Weather();
        Location location = new Location();
        Pollution pollution = new Pollution();
        location.setType("point");
        location.setCoordinates(null);
        pollution.setTime(LocalDate.now().toString());
        pollution.setAqius(27);
        weather.setHumidity(27);
        weather.setPressure(14);
        weather.setTemperature(10);
        weather.setWind(4);
        request.setWeather(weather);
        request.setLocation(location);
        request.setPollution(pollution);
        when(service.addToWishList(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = objectMapper.writeValueAsString(response);
        String url = "/wishlist/add";
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
        verify(service).addToWishList(request);

    }

    /**
     * scenario: When City already exists
     * input : AddToWatchList request
     * expectation:  CityInfoAlreadyExistsException is thrown. status 409/CONFLICT
     */
    @Test
    public void testAdd_2() throws Exception {

        AddToWatchList request = new AddToWatchList();
        request.setUserName("1");
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");
        Weather weather = new Weather();
        Location location = new Location();
        Pollution pollution = new Pollution();
        location.setType("point");
        location.setCoordinates(null);
        pollution.setTime(LocalDate.now().toString());
        pollution.setAqius(27);
        weather.setHumidity(27);
        weather.setPressure(14);
        weather.setTemperature(10);
        weather.setWind(4);
        request.setWeather(weather);
        request.setLocation(location);
        request.setPollution(pollution);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String msg = "City is already in the favourite list";
        CityInfoAlreadyExistsException e = new CityInfoAlreadyExistsException(msg);
        when(service.addToWishList(any(AddToWatchList.class))).thenThrow(e);
        String url = "/wishlist/add";
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isConflict())
                .andExpect(content().string(msg));

    }

        /**
     * scenario: When City is removed successfully
     * input : RemoveFromWatchList
     * expectation:  City is removed successfully. status 200/OK
     */
    @Test
    public void testRemove_1() throws Exception {
        RemoveFromWatchList request = new RemoveFromWatchList();
        request.setUserName("1");
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/wishlist/delete";
        mvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
        verify(service).remove(request);
    }


    /**
     * scenario: When City is not found
     * input : RemoveFromWatchList
     * expectation:  CityNotFoundException. status 404/NOT_FOUND
     */
    @Test
    public void testRemove_2() throws Exception {
        RemoveFromWatchList request = new RemoveFromWatchList();
        request.setUserName("1");
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/wishlist/delete";


        String msg = "No Wish found";
        CityInfoNotFoundException e = new CityInfoNotFoundException(msg);
        doThrow(e).when(service).remove(request);
        mvc.perform(delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(content().string(msg));
        verify(service).remove(request);
    }
}