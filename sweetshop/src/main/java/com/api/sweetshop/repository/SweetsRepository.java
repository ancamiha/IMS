package com.api.sweetshop.repository;

import com.api.sweetshop.model.Sweets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SweetsRepository extends JpaRepository<Sweets, Long> {
    Sweets getByName(String name);

    boolean existsByName(String name);
}
