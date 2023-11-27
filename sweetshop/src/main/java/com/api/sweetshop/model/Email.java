package com.api.sweetshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Email {
    private UserProfile user;
    private UserProfile from;
    private List<UserProfile> to;
    private String title, body;

    public Email(UserProfile user, UserProfile from, List<UserProfile> to, String title, String body) {
        this.user = user;
        this.from = from;
        this.to = to;
        this.title = title;
        this.body = body;
    }

    public Email() {

    }
}
