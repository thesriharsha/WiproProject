package com.stackroute.wishlistms.dto;

import com.stackroute.wishlistms.entity.Location;
import com.stackroute.wishlistms.entity.Pollution;
import com.stackroute.wishlistms.entity.Weather;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Request DTO for adding product
 */
public class AddToWatchList {
    private String userName;
    @NotBlank
    @Length(min = 2)
    private String city;
    @NotBlank
    @Length(min = 2)
    private String state;
    @NotBlank
    @Length(min = 2)
    private String country;

    private Location location;
    private Pollution pollution;
    private Weather weather;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddToWatchList that = (AddToWatchList) o;
        return Objects.equals(userName, that.userName) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, city, state, country);
    }
}
