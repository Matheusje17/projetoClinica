package com.project.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clinica.domain.AuthImage;


public interface ImagemRepository extends JpaRepository<AuthImage, Integer>{
	
	@Query("SELECT auth FROM AuthImage auth WHERE auth.isSorteada = ?1")
	AuthImage findSortedas(boolean isSorteada);
}
	