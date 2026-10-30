package com.hcld2.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository {
    List<Post> findAllbyOrderByCreatedAtDexc();










}
