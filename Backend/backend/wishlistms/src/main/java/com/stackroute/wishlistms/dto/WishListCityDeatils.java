package com.stackroute.wishlistms.dto;

import com.stackroute.wishlistms.entity.Location;
import com.stackroute.wishlistms.entity.Pollution;
import com.stackroute.wishlistms.entity.Weather;

/**
 * response DTO
 */
public class WishListCityDeatils {

    private String userName;
    private String city;
    private String state;
    private String country;
    private Location location;
    private Pollution pollution;
    private Weather weather;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }


}
