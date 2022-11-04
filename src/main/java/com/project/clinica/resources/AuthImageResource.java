package com.project.clinica.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.clinica.business.SorteioBusiness;
import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.dtos.ImageDTO;
import com.project.clinica.services.AuthImageService;

@RestController
@RequestMapping("/cadastrarImagem")
public class AuthImageResource {
	
	@Autowired
	AuthImageService authImgService;
	
	
	@PostMapping
	public ResponseEntity<ImageDTO> saveImage(@RequestParam("file") MultipartFile img) {
		AuthImage newObj = authImgService.save(img);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(newObj.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/*
	 * @GetMapping() public ResponseEntity<ImageDTO> sorteiaImagem() {
	 * List<AuthImage> imagensToSort = authImgService.getAllAuthImages();
	 * //System.out.println("dsadas" + imagensToSort);
	 * 
	 * try { SorteioBusiness sorteioBussiness = new SorteioBusiness(); ImageDTO
	 * sorteada = sorteioBussiness.realizaSorteio(imagensToSort);
	 * authImgService.update(sorteada); return ResponseEntity.ok().body(sorteada); }
	 * catch(IOException e) { System.out.println("Erro:" + e.getMessage()); } return
	 * null; }
	 */
	

	@GetMapping(value = "inativar")
	public ResponseEntity<ImageDTO> inativaImagens() {
		List<AuthImage> imagensToSort = authImgService.getAllAuthImages();
		SorteioBusiness sorteioBussiness = new SorteioBusiness();
		
		authImgService.update(sorteioBussiness.inativarSorteio(imagensToSort));
		
		return ResponseEntity.ok().body(null);

	}
	
	//TODO -> Mudar para receber como parametro o profileDeveloperName
	@PostMapping(value = "autorizarLogin")
	public ResponseEntity<AuthImageResource.CustomResponse> autorizarLoginImagens(@RequestBody AuthImageResource.CustomRequest obj) {
		Boolean result = authImgService.autorizarLogin(obj);
		CustomResponse customResponse = new CustomResponse(result);
		return ResponseEntity.ok().body(customResponse);

	}
	
	
	public static class CustomRequest{
		private Integer codigoImg;
		private String profileDeveloperName;
		
		public CustomRequest() {
			super();
		}

		public Integer getCodigoImg() {
			return codigoImg;
		}

		public void setCodigoImg(Integer codigoImg) {
			this.codigoImg = codigoImg;
		}

		public String getProfileDeveloperName() {
			return profileDeveloperName;
		}

		public void setProfileDeveloperName(String profileDeveloperName) {
			this.profileDeveloperName = profileDeveloperName;
		}
		
	}
	
	public static class CustomResponse{
		String isAutorizado;
		
		public CustomResponse(Boolean isAutorizado) {
			if (isAutorizado) {
				this.isAutorizado = "autorizado";
			}
			else {
				this.isAutorizado = "inautorizado";
			}
		}

		public String getIsAutorizado() {
			return isAutorizado;
		}

		public void setIsAutorizado(String isAutorizado) {
			this.isAutorizado = isAutorizado;
		}
		
		
		
	}
		
}