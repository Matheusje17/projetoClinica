package com.project.clinica.domain.dtos;

import com.project.clinica.domain.AuthProfileClass;

public class AuthProfileClassDTO {	
	
	public Integer id;
	
	public String profileDeveloperName;
	
	public String sortedImage;

	public AuthProfileClassDTO() {}
	
	public AuthProfileClassDTO(String profileDeveloperName) {
		super();
		this.profileDeveloperName = profileDeveloperName;
	}
	
	public AuthProfileClassDTO(AuthProfileClass authProfile) {
		this.id = authProfile.getId();
		this.profileDeveloperName = authProfile.getProfileDeveloperName();
		this.sortedImage = authProfile.getSortedImageCod();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id  = id;
	}
	
	public String getProfileDeveloperName() {
		return profileDeveloperName;
	}

	public void setProfileDeveloperName(String profileDeveloperName) {
		this.profileDeveloperName = profileDeveloperName;
	}

	public String getSortedImage() {
		return sortedImage;
	}

	public void setSortedImage(String sortedImage) {
		this.sortedImage = sortedImage;
	}
	
	
	
}
