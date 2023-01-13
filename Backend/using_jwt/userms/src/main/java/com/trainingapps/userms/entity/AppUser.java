package com.trainingapps.userms.entity;


import javax.persistence.*;
import java.util.Objects;

@Table(name="users")
@Entity
public class AppUser {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable=false,unique=true)
    private String username;

    @Column(nullable=false)
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser user = (AppUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
