package com.api.sweetshop.service;

import com.api.sweetshop.exceptions.UserExistsException;
import com.api.sweetshop.model.UserProfile;

import java.util.Set;

public interface Baking {

    Set<UserProfile> getUsers();

    UserProfile getUser(int poz);

    void addUser(UserProfile user) throws UserExistsException;
}
