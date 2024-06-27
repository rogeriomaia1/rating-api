package com.datapar.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datapar.model.Rating;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
	 Optional<Rating> findByEmail(String email);
}

