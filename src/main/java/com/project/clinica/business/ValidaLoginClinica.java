package com.project.clinica.business;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.project.clinica.domain.AuthImage;

public class ValidaLoginClinica {
	private static final Component OBSERVER = new Component() {

		private static final long serialVersionUID = 1L;};

	public Boolean doValidacao(AuthImage imgEscolhidaUser, AuthImage imgSorteada) {
		
		byte[] bytesEscolhida = imgEscolhidaUser.getImagem();
		byte[] bytesSorteada = imgSorteada.getImagem();
		
		InputStream inputStreamEscolhida = new ByteArrayInputStream(bytesEscolhida);
		InputStream inputStreamSorteada = new ByteArrayInputStream(bytesSorteada);
		
		try {
			BufferedImage biEcolhida = ImageIO.read(inputStreamEscolhida);
			BufferedImage biSorteada = ImageIO.read(inputStreamSorteada);
			
			return compararImagens(biEcolhida, biSorteada);
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	//##################################
	//#			PRIVATE METHODS	       #
	//##################################	
	
	private Boolean compararImagens (BufferedImage biEcolhida, BufferedImage biSorteada) {
		
		Boolean result = false;

		int w1 = biEcolhida.getWidth(OBSERVER);
		int h1 = biEcolhida.getHeight(OBSERVER);
		
		int w2 = biSorteada.getWidth(OBSERVER);
		int h2 = biSorteada.getHeight(OBSERVER);
		
		int sizeEscolhida = w1 * h1;
		int sizeSorteada = w2 * h2;
		
		PixelGrabber pixelGrabber1 = new PixelGrabber(biEcolhida, 0, 0, w1, h1, new int[sizeEscolhida], 0, w1);
		PixelGrabber pixelGrabber2 = new PixelGrabber(biSorteada, 0, 0, w2, h2, new int[sizeSorteada], 0, w2);
		
		pixelGrabber1.startGrabbing();
		pixelGrabber2.startGrabbing();

		int[] array1 = (int[]) pixelGrabber1.getPixels();
		int[] array2 = (int[]) pixelGrabber2.getPixels();
		
		if (Arrays.equals(array1, array2)) {
			result = true;
		}

		return result;


	}

}
