package com.project.clinica.domain.dtos;

import java.io.Serializable;

import com.project.clinica.domain.AuthImage;

public class ImageDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Integer codigo;
	
	protected byte[] imagem;

	protected String fileName;
	
	protected String fileType;
	
	protected Boolean isSorteada;
	
	public ImageDTO() {}

	public ImageDTO( byte[] imagem, String fileName, String fileType) {
		super();
		this.imagem = imagem;
		this.fileName = fileName;
		this.fileType = fileType;
	}
	
	public ImageDTO(AuthImage objImg) {
		super();
		this.codigo = objImg.getCodigo();
		this.imagem = objImg.getImagem();
		this.fileName = objImg.getFileName();
		this.fileType = objImg.getFileType();
		this.isSorteada = objImg.getIsSorteada();
	}
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getIsSorteada() {
		return isSorteada;
	}

	public void setIsSorteada(Boolean isSorteada) {
		this.isSorteada = isSorteada;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	
	

	
	

}
