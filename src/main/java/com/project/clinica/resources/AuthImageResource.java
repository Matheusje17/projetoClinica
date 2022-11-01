package com.project.clinica.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping()
	public ResponseEntity<ImageDTO> sorteiaImagem() {
		List<AuthImage> imagensToSort = authImgService.getAllAuthImages();
		//System.out.println("dsadas" + imagensToSort);
		
		try {
			SorteioBusiness sorteioBussiness = new SorteioBusiness();
			ImageDTO sorteada = sorteioBussiness.realizaSorteio(imagensToSort);
			authImgService.update(sorteada);
			return ResponseEntity.ok().body(sorteada);
		}
		catch(IOException e) {
			System.out.println("Erro:" + e.getMessage());
		}
		return null;	
	}
	

	@GetMapping(value = "inativar")
	public ResponseEntity<ImageDTO> inativaImagens() {
		List<AuthImage> imagensToSort = authImgService.getAllAuthImages();
		SorteioBusiness sorteioBussiness = new SorteioBusiness();
		
		authImgService.update(sorteioBussiness.inativarSorteio(imagensToSort));
		
		return ResponseEntity.ok().body(null);

	}
	

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<ImageDTO> autorizarLoginImagens(@PathVariable Integer codigo) {
		authImgService.autorizarLogin(codigo);
		return ResponseEntity.ok().body(null);

	}

}