package com.dseliel.crud.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dseliel.crud.dto.FilmeDTO;
import com.dseliel.crud.entities.Filme;
import com.dseliel.crud.repository.FilmeRepository;

@Service
public class FilmeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	public FilmeRepository repo;

	public List<FilmeDTO> findAll() {
		List<Filme> response = repo.findAll();
		return response.stream().map(x -> new FilmeDTO(x)).collect(Collectors.toList());
	}

	public FilmeDTO findById(Long id) {
		Filme response = repo.findById(id).get();
		return new FilmeDTO(response);
	}

	public FilmeDTO adicionar(FilmeDTO obj) {
		Filme filme = new Filme(null, obj.getNome(), obj.getCategoria());
		repo.save(filme);
		return new FilmeDTO(filme);
	}

	public void deletar(Long id) {
		repo.deleteById(id);
	}

	public FilmeDTO atualizar(Long id, FilmeDTO dto) {
		Filme newFilme = repo.findById(id).get();
		newFilme.setNome(dto.getNome());
		newFilme.setCategoria(dto.getCategoria());

		repo.save(newFilme);

		return new FilmeDTO(newFilme);
	}
}
