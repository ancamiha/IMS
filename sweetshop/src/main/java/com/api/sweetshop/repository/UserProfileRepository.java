package com.api.sweetshop.repository;

import com.api.sweetshop.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmailAndPassword(String email, String password);
}
