package com.Vicvin.Bookflix.repository;

import com.Vicvin.Bookflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
