package com.onlineocr.nick.model.entity;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by GrIfOn on 08.01.2017.
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @NotNull
    @Column(name = "u_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "u_name")
    private String name;

    @Column(name = "u_lastName")
    private String lastName;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_passwordHash")
    private String passwordHash;

    @Column(name = "u_login")
    private String login;

    @Column(name = "u_birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "u_country")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "u_city")
    @Cascade(CascadeType.SAVE_UPDATE)
    private City city;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
