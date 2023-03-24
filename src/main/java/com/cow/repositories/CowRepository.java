package com.cow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cow.models.Cow;

public interface CowRepository extends JpaRepository<Cow, String>{
	

}

