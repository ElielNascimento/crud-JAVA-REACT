package com.dseliel.crud.controller;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dseliel.crud.dto.FilmeDTO;
import com.dseliel.crud.service.FilmeService;

@RestController
@RequestMapping(value = "api/filmes")
@CrossOrigin("*")
public class FilmeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmeService filmeService;

	@GetMapping
	private ResponseEntity<List<FilmeDTO>> findAll() {
		List<FilmeDTO> response = filmeService.findAll();
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		filmeService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	private ResponseEntity<FilmeDTO> adicionar(@RequestBody @Valid FilmeDTO obj) {
		obj = filmeService.adicionar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	private ResponseEntity<FilmeDTO> atualizar(@PathVariable Long id, @RequestBody FilmeDTO filme) {
		FilmeDTO response = filmeService.atualizar(id, filme);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	private ResponseEntity<FilmeDTO> buscarPeloId(@PathVariable Long id) {
		FilmeDTO response = filmeService.findById(id);
		return ResponseEntity.ok().body(response);
	}

}
