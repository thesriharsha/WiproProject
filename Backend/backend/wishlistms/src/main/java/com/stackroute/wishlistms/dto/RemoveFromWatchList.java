package com.stackroute.wishlistms.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RemoveFromWatchList {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveFromWatchList that = (RemoveFromWatchList) o;
        return Objects.equals(userName, that.userName) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, city, state, country);
    }
}
