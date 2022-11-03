package com.project.clinica.services;

import java.io.IOException;
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
	
	public AuthProfileClass update(Integer id, AuthProfileClassDTO objDTO) {
		objDTO.setId(id);
		AuthProfileClass oldAuthProfile = profileRepository.findById(id).orElse(null);
		
		oldAuthProfile = new AuthProfileClass(objDTO);
		return profileRepository.save(oldAuthProfile);
	}
	
	public AuthProfileClass sortImage(String profileDevoloperName, List<AuthImage> listToSort) throws IOException {
		SorteioBusiness sorteio = new SorteioBusiness();
		AuthProfileClass auth = profileRepository.findByProfileDeveloperName(profileDevoloperName).orElse(null);
		AuthImage imgSorteada = new AuthImage(sorteio.realizaSorteio(listToSort));
		
		auth.setSortedImageCod(imgSorteada.getCodigo().toString());
		return profileRepository.save(auth);
	}
	
}
