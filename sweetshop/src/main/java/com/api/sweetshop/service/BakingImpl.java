package com.api.sweetshop.service;

import com.api.sweetshop.exceptions.UserExistsException;
import com.api.sweetshop.model.Gender;
import com.api.sweetshop.model.UserProfile;

import java.util.*;

public class BakingImpl implements Baking {

    private final Set<UserProfile> users = new LinkedHashSet<>();

    private final UserProfile bakingSystem = new UserProfile("Baking System", null, Gender.NaN);
    private final UserProfile bakingSystemAdmin = new UserProfile("System Admin", null, Gender.NaN);
    private List<UserProfile> toList = new ArrayList<>();

    private Long currentId = 0L;

    private int printedUsers = 0;

    public BakingImpl() {
        toList.add(bakingSystemAdmin);
    }

    public int getPrintedUsers() {
        return printedUsers;
    }

    public void addUser(final UserProfile user) throws UserExistsException {
        if (users.contains(user)) {
            throw new UserExistsException("Client already exists into the bank");
        }

        users.add(user);
    }

    @Override
    public Set<UserProfile> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    @Override
    public UserProfile getUser(int poz) {
        int i = 0;
        for (UserProfile client : users) {
            if (i == poz)
                return client;
            i++;
        }
        return null;
    }
}
