package com.api.sweetshop.service;

import com.api.sweetshop.model.Gender;
import com.api.sweetshop.model.UserProfile;

import java.util.*;
import java.util.stream.Collectors;

public class BakingStreams {
    public int getNumberOfUsers(BakingImpl baking) {
        return baking.getUsers().size();
    }

    public int getNumberOfFavoriteSweets(BakingImpl baking) {
        return baking.getUsers()
            .stream()
            .map(user -> user.getFavoriteSweets().size())
            .reduce(0, Integer::sum);
    }

    public SortedSet<UserProfile> getUsersSorted(BakingImpl baking) {
        return baking.getUsers()
            .stream()
            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserProfile::getName))));
    }

    public Map<Gender, List<UserProfile>> getUsersByGender(BakingImpl baking) {
        return baking.getUsers()
            .stream()
            .sorted(Comparator.comparing(UserProfile::getGender))
            .collect(Collectors.groupingBy(UserProfile::getGender, LinkedHashMap::new, Collectors.toList()));
    }
}
