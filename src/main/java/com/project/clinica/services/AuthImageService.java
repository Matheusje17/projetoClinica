package com.project.clinica.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.clinica.business.ValidaLoginClinica;
import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.dtos.ImageDTO;
import com.project.clinica.repositories.AuthProfileRepository;
import com.project.clinica.repositories.ImagemRepository;
import com.project.clinica.resources.AuthImageResource;

@Service
public class AuthImageService {
	
	@Autowired
	ImagemRepository imgRepository;
	
	@Autowired
	AuthProfileRepository authProfileRepository;
	
	public AuthImage save(MultipartFile file) {
		System.out.println("ERROOOOOOOOOOOOO " +file);
		AuthImage	img = uploadFile(file);	
		return img;
	
	}
	
	public AuthImage update(ImageDTO img) {
		try {
			AuthImage newObj = new AuthImage(img);
			return imgRepository.save(newObj);
		}
		catch(Exception e) {
			System.out.println("ERROOOOOOOOOOOOO " +e.getMessage());
			return null;
		}
	
	}
	
	public List<AuthImage> update(List<ImageDTO> imgs) {
		List<AuthImage> imgsToInativate = new ArrayList<AuthImage>();
		
		for (ImageDTO imgDTO : imgs) {
			AuthImage auth = new AuthImage(imgDTO);
			imgsToInativate.add(auth);
		}
		
		return imgRepository.saveAll(imgsToInativate);
	}
	
	public Boolean autorizarLogin(AuthImageResource.CustomRequest obj) {
		AuthImage imgEscolhidaUser = imgRepository.findById(obj.getCodigoImg()).orElse(null);
		String imgCod = authProfileRepository.findByProfileDeveloperName(obj.getProfileDeveloperName()).orElse(null).getSortedImageCod();
		AuthImage imgSorteada = imgRepository.findById(Integer.valueOf(imgCod)).orElse(null);
		
		ValidaLoginClinica validaLoginClinica = new ValidaLoginClinica();
		
		return validaLoginClinica.doValidacao(imgEscolhidaUser, imgSorteada);
	}
	
	public List<AuthImage> getAllAuthImages() {
		return imgRepository.findAll();
	}
	
//##################################
//#			PRIVATE METHODS	       #
//##################################	

	private AuthImage uploadFile(MultipartFile file) {
		try {
			byte[] img =  file.getBytes();
			String fileName = file.getOriginalFilename();
			String imgType = file.getContentType();
			
			ImageDTO imgDTO = new ImageDTO( img, fileName, imgType);
			
			AuthImage newObj = new AuthImage(imgDTO);
			
			return imgRepository.save(newObj);
		}
		catch(Exception e) {
			System.out.println("ERROOOOOOOOOOOOO " +e.getMessage());
			return null;
		}
	}

}
