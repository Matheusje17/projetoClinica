package com.project.clinica.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.clinica.domain.dtos.AuthProfileClassDTO;

@Entity
public class AuthProfileClass implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	protected String profileDeveloperName;
	
	protected String sortedImage;


	public AuthProfileClass() {}

	public AuthProfileClass(String profileDeveloperName) {
		super();
		this.profileDeveloperName = profileDeveloperName;
	}
	
	public AuthProfileClass(AuthProfileClassDTO objDTO) {
		this.profileDeveloperName = objDTO.profileDeveloperName;
		this.sortedImage = objDTO.sortedImage;
	}

	
	public Integer getId() {
		return id;
	}
	

	public String getProfileDeveloperName() {
		return profileDeveloperName;
	}

	public void setProfileDeveloperName(String profileDeveloperName) {
		this.profileDeveloperName = profileDeveloperName;
	}

	public String getSortedImageCod() {
		return sortedImage;
	}

	public void setSortedImageCod(String sortedImage) {
		this.sortedImage = sortedImage;
	}
	
	
	
}
