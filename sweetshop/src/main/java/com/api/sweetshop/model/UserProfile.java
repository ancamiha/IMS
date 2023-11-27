package com.api.sweetshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    @Transient
    private Gender gender;
    @Transient
    private Set<Sweet> favoriteSweets = new HashSet<>();

    public UserProfile() {

    }

    public UserProfile(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public UserProfile(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserProfile(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addFavoriteSweet(Sweet sweet) {
        favoriteSweets.add(sweet);
    }
}
