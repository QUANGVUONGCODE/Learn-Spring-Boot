package com.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {

}
