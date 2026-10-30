package com.hcld2.jwtdemo.repository;

import com.hcld2.jwtdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository {
    Optional<User> findByUserName(String);

}
