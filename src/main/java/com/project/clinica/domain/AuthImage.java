package com.project.clinica.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.project.clinica.domain.dtos.ImageDTO;


@Entity
public class AuthImage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer codigo;
	
	@Lob
	protected byte[] imagem;

	protected String fileName;
	
	protected String fileType;
	
	protected Boolean isSorteada;
	
	public AuthImage() {}
	
	public AuthImage(ImageDTO objDTO) {
		super();
		this.codigo = objDTO.getCodigo();
		this.imagem = objDTO.getImagem();
		this.fileName = objDTO.getFileName();
		this.fileType = objDTO.getFileType();
		this.isSorteada = objDTO.getIsSorteada();
	}

	public AuthImage(Integer codigo, byte[] imagem, String fileName, String fileType) {
		super();
		this.codigo = codigo;
		this.imagem = imagem;
		this.fileName = fileName;
		this.fileType = fileType;
		this.isSorteada = false;
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

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthImage other = (AuthImage) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
	
	
}
