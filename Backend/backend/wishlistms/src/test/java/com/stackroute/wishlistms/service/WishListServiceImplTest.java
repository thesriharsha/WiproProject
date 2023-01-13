package com.stackroute.wishlistms.service;

import com.stackroute.wishlistms.dto.AddToWatchList;
import com.stackroute.wishlistms.dto.RemoveFromWatchList;
import com.stackroute.wishlistms.dto.WishListCityDeatils;
import com.stackroute.wishlistms.entity.CityInfo;
import com.stackroute.wishlistms.entity.Location;
import com.stackroute.wishlistms.entity.Pollution;
import com.stackroute.wishlistms.entity.Weather;
import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import com.stackroute.wishlistms.repository.ICityInfoRepository;
import com.stackroute.wishlistms.util.WishlistCityUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishListServiceImplTest {
    @Mock
    ICityInfoRepository repository;
    @Mock
    WishlistCityUtil util;
    @InjectMocks
    @Spy
    WishListServiceImpl service;

      /*
      Scenario: when City List is fetched successfully
      Input: UserID:10
      expectation : List is Succesfully Fetched
      ICityInfoRepository#findByUserId(UserId) is called Once
      */
    @Test
    public void testWatchListByUserId_1() throws Exception {
        String UserName = "10";
        List<WishListCityDeatils> detailsList = mock(List.class);
        List<CityInfo> cityInfos = mock(List.class);
        when(cityInfos.isEmpty()).thenReturn(false);
        when(repository.findByUserName(UserName)).thenReturn(cityInfos);
        when(util.toListCityDetails(cityInfos)).thenReturn(detailsList);
        List<WishListCityDeatils> result = service.listWatchListByUserName(UserName);
        assertSame(detailsList, result);
        verify(repository).findByUserName(UserName);
        verify(util).toListCityDetails(cityInfos);
    }
      /*
      Scenario: when City List is empty
      Input: UserID:10
      expectation : CityInfoNotFoundException is thrown
      ICityInfoRepository#findByUserId(UserId) is called Once
      */

    @Test
    public void testWatchListByUserId_2() throws Exception {
        String UserName = "10";
        List<CityInfo> cityInfoList = mock(List.class);
        when(cityInfoList.isEmpty()).thenReturn(true);
        when(repository.findByUserName(UserName)).thenReturn(cityInfoList);
        Executable executable = () -> {
            service.listWatchListByUserName(UserName);
        };

        assertThrows(CityInfoNotFoundException.class, executable);
        verify(repository).findByUserName(UserName);

    }

      /*
         Scenario: When City is added successfully
         Input: AddToWatchList request
        expectation : City is added and WishListCityDeatils is returned
        verify repository#save(favoriteCity) is called once
     */

    @Test
    public void testAddToWatchList_1() throws Exception {
        AddToWatchList request = new AddToWatchList();
        Location locationOne = new Location();
        Pollution pollutionOne = new Pollution();
        Weather weatherOne = new Weather();
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");
        request.setUserName("10");
        locationOne.setType("point");
        locationOne.setCoordinates(null);
        pollutionOne.setTime(LocalDate.now().toString());
        pollutionOne.setAqius(27);
        weatherOne.setHumidity(27);
        weatherOne.setPressure(14);
        weatherOne.setTemperature(10);
        weatherOne.setWind(4);
        request.setWeather(weatherOne);
        request.setPollution(pollutionOne);
        request.setLocation(locationOne);
        String id = "123";

        doReturn(id).when(service).generateId(request.getUserName(), request.getCity(), request.getState(), request.getCountry());

        CityInfo favouriteCity = mock(CityInfo.class);
        CityInfo savedCityInfo = mock(CityInfo.class);
        when(repository.save(favouriteCity)).thenReturn(savedCityInfo);
        Optional<CityInfo> optional = Optional.empty();
        when(util.toCityInfo(request)).thenReturn(favouriteCity);
        WishListCityDeatils details = mock(WishListCityDeatils.class);
        when(util.toCityDetails(savedCityInfo)).thenReturn(details);
        when(repository.findByUserNameAndCityAndStateAndCountry(request.getUserName(), request.getCity(), request.getState(), request.getCountry()))
                .thenReturn(optional);
        WishListCityDeatils result = service.addToWishList(request);
        assertSame(details, result);
        verify(repository).save(favouriteCity);

    }

     /*
        Scenario: When City is already Exist
        Input: AddToWatchList request
        expectation : CityInfoAlreadyExistsException is thrown
        verify repository#save(favoriteCity) is never called
     */

    @Test
    public void testAddToWatcList_2() throws Exception {
        AddToWatchList request = new AddToWatchList();
        Location locationOne = new Location();
        Pollution pollutionOne = new Pollution();
        Weather weatherOne = new Weather();
        request.setCity("delhi");
        request.setState("delhi");
        request.setCountry("india");
        request.setUserName("10");
        locationOne.setType("point");
        locationOne.setCoordinates(null);
        pollutionOne.setTime(LocalDate.now().toString());
        pollutionOne.setAqius(27);
        weatherOne.setHumidity(27);
        weatherOne.setPressure(14);
        weatherOne.setTemperature(10);
        weatherOne.setWind(4);
        request.setWeather(weatherOne);
        request.setPollution(pollutionOne);
        request.setLocation(locationOne);
        CityInfo favCityInfo = mock(CityInfo.class);
        Optional<CityInfo> optional = Optional.of(favCityInfo);
        when(repository.findByUserNameAndCityAndStateAndCountry(request.getUserName(), request.getCity(), request.getState(), request.getCountry()))
                .thenReturn(optional);
        Executable executable = () -> {
            service.addToWishList(request);
        };
        assertThrows(CityInfoAlreadyExistsException.class, executable);
        verify(repository, never()).save(favCityInfo);
    }

      /*
        Scenario: When City is deleted Successfully
        Input: RemoveFromWatchList removeRequest
        expectation : City is deleted successfully
        ICityInfoRepository#remove(UserId) is called Once
     */

    @Test
    public void remove_1() throws CityInfoNotFoundException {
        RemoveFromWatchList removeRequest = new RemoveFromWatchList();
        removeRequest.setUserName("10");
        removeRequest.setState("delhi");
        removeRequest.setCity("delhi");
        removeRequest.setCountry("india");
        CityInfo cityInfo = mock(CityInfo.class);
        Optional<CityInfo> optional = Optional.of(cityInfo);
        when(repository.findByUserNameAndCityAndStateAndCountry(removeRequest.getUserName(), removeRequest.getCity(), removeRequest.getState(), removeRequest.getCountry())).thenReturn(optional);
        service.remove(removeRequest);
        verify(repository).delete(cityInfo);
    }

        /*
            Scenario: When City is not present
            Input: RemoveFromWatchList removeRequest
            expectation : CityInfoNotFoundException is thrown
            ICityInfoRepository#remove(UserId) is never called
         */

    @Test
    public void remove_2() throws Exception {
        RemoveFromWatchList removeRequest = new RemoveFromWatchList();
        removeRequest.setUserName("100");
        removeRequest.setState("delhi");
        removeRequest.setCity("delhi");
        removeRequest.setCountry("india");
        CityInfo cityInfo = mock(CityInfo.class);
        Optional<CityInfo> optional = Optional.empty();
        when(repository.findByUserNameAndCityAndStateAndCountry(removeRequest.getUserName(), removeRequest.getCity(), removeRequest.getState(), removeRequest.getCountry())).thenReturn(optional);
        Executable executable = () -> {
            service.remove(removeRequest);
        };
        assertThrows(CityInfoNotFoundException.class, executable);
        verify(repository, never()).delete(cityInfo);
    }

}