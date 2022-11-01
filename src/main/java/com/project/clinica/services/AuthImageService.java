package com.project.clinica.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.clinica.business.ValidaLoginClinica;
import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.dtos.ImageDTO;
import com.project.clinica.repositories.ImagemRepository;

@Service
public class AuthImageService {
	
	@Autowired
	ImagemRepository imgRepository;
	
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
	
	public void autorizarLogin(Integer cogigo) {
		AuthImage imgEscolhidaUser = imgRepository.findById(cogigo).orElse(null);
		AuthImage imgSorteada = imgRepository.findSortedas(true);
		
		ValidaLoginClinica validaLoginClinica = new ValidaLoginClinica();
		
		validaLoginClinica.doValidacao(imgEscolhidaUser, imgSorteada);
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
