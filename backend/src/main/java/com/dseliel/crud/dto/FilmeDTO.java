package com.dseliel.crud.dto;

import java.io.Serializable;

import com.dseliel.crud.entities.Filme;

public class FilmeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String categoria;

	public FilmeDTO() {
	}

	public FilmeDTO(Long id, String nome, String categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}

	public FilmeDTO(Filme entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.categoria = entity.getCategoria();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

}
