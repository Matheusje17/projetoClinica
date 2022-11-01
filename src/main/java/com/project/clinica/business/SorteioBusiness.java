package com.project.clinica.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.project.clinica.domain.AuthImage;
import com.project.clinica.domain.dtos.ImageDTO;

public class SorteioBusiness {
	
	public ImageDTO realizaSorteio(List<AuthImage> imageToSort) throws IOException {
		
		Random sorteio = new Random();
		int sorteado = sorteio.nextInt(imageToSort.size());		
		ImageDTO sorteada = new ImageDTO(imageToSort.get(sorteado));
		sorteada.setIsSorteada(true);
		
		return sorteada;		
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
