package com.project.clinica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clinica.domain.AuthProfileClass;


public interface AuthProfileRepository extends JpaRepository<AuthProfileClass, Integer>{
	
	Optional<AuthProfileClass> findByProfileDeveloperName(String profileDeveloperName);
	
	
	
}
	