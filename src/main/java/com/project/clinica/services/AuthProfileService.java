package com.project.clinica.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.business.SorteioBusiness;
import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.AuthProfileClass;
import com.project.clinica.domain.dtos.AuthProfileClassDTO;
import com.project.clinica.repositories.AuthProfileRepository;

@Service
public class AuthProfileService {
	
	
	@Autowired
	AuthProfileRepository profileRepository;
	
	public AuthProfileClass save(AuthProfileClassDTO objDTO) {
		AuthProfileClass auth = new AuthProfileClass(objDTO);
		return profileRepository.save(auth);
	
	}
	
	public List<AuthProfileClass> getAllAuthProfileClass() {
		return profileRepository.findAll();
	}
	
	public AuthProfileClass update(Integer id, AuthProfileClassDTO objDTO) {
		objDTO.setId(id);
		AuthProfileClass oldAuthProfile = profileRepository.findById(id).orElse(null);
		
		oldAuthProfile = new AuthProfileClass(objDTO);
		return profileRepository.save(oldAuthProfile);
	}
	
	public List<AuthProfileClass> sortImage(List<AuthProfileClass> profiles , List<AuthImage> listToSort) throws IOException {
		SorteioBusiness sorteio = new SorteioBusiness();
		//AuthProfileClass auth = profileRepository.findByProfileDeveloperName(profileDevoloperName).orElse(null);
		
		List<AuthProfileClass> profilesToUpdate = new ArrayList<>();
		
		for ( AuthProfileClassDTO profileDTO: sorteio.realizaSorteio(profiles, listToSort)) {
			profilesToUpdate.add(new AuthProfileClass(profileDTO));
		}
		
		return profileRepository.saveAll(profilesToUpdate);
	}
	
}
