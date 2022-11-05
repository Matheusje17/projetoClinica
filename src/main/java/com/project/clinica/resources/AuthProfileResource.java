package com.project.clinica.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.clinica.domain.AuthProfileClass;
import com.project.clinica.domain.dtos.AuthProfileClassDTO;
import com.project.clinica.domain.dtos.ImageDTO;
import com.project.clinica.services.AuthImageService;
import com.project.clinica.services.AuthProfileService;

@RestController
@RequestMapping("/authProfile")
public class AuthProfileResource {

	@Autowired
	AuthProfileService authProfileService;
	
	@Autowired
	AuthImageService authImageService;

	@PostMapping
	public ResponseEntity<ImageDTO> saveAuthProfile(@RequestBody AuthProfileClassDTO authProfile) {
		AuthProfileClass newObj;
		try {
			newObj = authProfileService.save(authProfile);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	@GetMapping
	public ResponseEntity<List<CustomResponse>> sortImage() {
		try {
			//AuthProfileClass result; 
			List<AuthProfileClass> authProfileList = authProfileService.sortImage(authProfileService.getAllAuthProfileClass() ,authImageService.getAllAuthImages());	
			
			//CustomResponse response = new CustomResponse(null);
			List<CustomResponse> results = authProfileList.stream().map(authProfile -> new CustomResponse(authProfile)).collect(Collectors.toList());
			return ResponseEntity.ok().body(results);
		}
		catch (IOException e) {
			System.out.println("ERRO" + e.getMessage());
		}	
		
		return null;
	}
	
	public class CustomResponse{
		private String codigoImg;
		private String profileDeveloperName;
		
		public CustomResponse() {
			super();
		}

		public CustomResponse(AuthProfileClass obj) {
			super();
			this.codigoImg = obj.getSortedImageCod();
			this.profileDeveloperName = obj.getProfileDeveloperName();
		}

		public String getCodigoImg() {
			return codigoImg;
		}

		public void setCodigoImg(String codigoImg) {
			this.codigoImg = codigoImg;
		}

		public String getProfileDeveloperName() {
			return profileDeveloperName;
		}

		public void setProfileDeveloperName(String profileDeveloperName) {
			this.profileDeveloperName = profileDeveloperName;
		}
		
	}

	
}