package com.example.CityZen;

import java.sql.Date;

public class UtenteBean {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String cf;
    private String birthDate;
    private String birthCity;
    private String sex;

    public UtenteBean(String username, String password, String name, String surname,
                      String cf, String birthDate, String birthCity, String sex) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.birthDate = birthDate;
        this.birthCity = birthCity;
        this.sex = sex;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    @Override
    public boolean equals(Object other) {
        return this.cf.equals(((UtenteBean) other).getCf());
    }

    @Override
    public String toString() {
        return "UserBean [username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname
                + ", cf=" + cf + ", birthDate=" + birthDate + ", birthCity=" + birthCity + ", sex=" + sex + "]";
    }

}
