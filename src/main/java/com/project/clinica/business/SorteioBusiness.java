package com.project.clinica.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.AuthProfileClass;
import com.project.clinica.domain.dtos.AuthProfileClassDTO;
import com.project.clinica.domain.dtos.ImageDTO;

public class SorteioBusiness {
	
	public List<AuthProfileClassDTO> realizaSorteio(List<AuthProfileClass> profiles,List<AuthImage> imageToSort) throws IOException {
		List<AuthProfileClassDTO> profilesToUpdate = new ArrayList<>();
		
		for (AuthProfileClass profile : profiles) {
			Random sorteio = new Random();
			int sorteado = sorteio.nextInt(imageToSort.size());
			
			profile.setSortedImageCod(imageToSort.get(sorteado).getCodigo().toString());
			profilesToUpdate.add(new AuthProfileClassDTO(profile));
		}
		
	
		//ImageDTO sorteada = new ImageDTO(imageToSort.get(sorteado));
		//sorteada.setIsSorteada(true);
		
		return profilesToUpdate;		
	}
	
	public List<ImageDTO> inativarSorteio(List<AuthImage> imageToSort) {
		List<ImageDTO> inativas = new ArrayList<ImageDTO>();
		
		for (AuthImage auth : imageToSort) {
			auth.setIsSorteada(false);
			ImageDTO imgDTO = new ImageDTO(auth);
			inativas.add(imgDTO);
		}
		
		return inativas;
	}
}
