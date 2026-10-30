package com.hcld2.jwtdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable= false)
    private String username;
    @Column(nullable = false)
    private String password;

}
