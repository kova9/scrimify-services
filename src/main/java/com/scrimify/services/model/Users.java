package com.scrimify.services.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDate dob;
    private String countryCode;
    @Column(name = "crttst")
    private ZonedDateTime createTimestamp;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<GameAccount> gameAccounts;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countyCode) {
        this.countryCode = countyCode;
    }

    public ZonedDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(ZonedDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public List<GameAccount> getGameAccounts() {
        return gameAccounts;
    }

    public void setGameAccounts(List<GameAccount> gameAccounts) {
        this.gameAccounts = gameAccounts;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
