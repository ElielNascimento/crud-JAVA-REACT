package com.dseliel.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dseliel.crud.dto.FilmeDTO;
import com.dseliel.crud.entities.Filme;
import com.dseliel.crud.repository.FilmeRepository;

@SpringBootTest
public class FilmeServiceTest {

	private static final String NOME = "VINGADORES";

	private static final long ID = 1L;

	private static final String CATEGORIA = "AÇÃO";

	@InjectMocks
	private FilmeService service;

	@Mock
	private FilmeRepository repo;

	@Mock
	private Filme filme;

	@Mock
	private FilmeDTO filmeDTO;

	private Optional<Filme> optionalFilme;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
		startFilme();

	}

	@Test
	void buscarUmaListaDeFilmes() {
		Mockito.when(repo.findAll()).thenReturn(List.of(filme));

		List<FilmeDTO> response = service.findAll();

		Assertions.assertNotNull(response);

		Assertions.assertEquals(FilmeDTO.class, filmeDTO.getClass());

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(CATEGORIA, filme.getCategoria());

	}

	@Test
	void buscarFilmesPeloId() {

		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);

		FilmeDTO response = service.findById(ID);

		Assertions.assertNotNull(response);

		assertEquals(filmeDTO.getClass(), response.getClass());

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(CATEGORIA, filme.getCategoria());

	}

	@Test
	void adicionarFilmeComSucesso() {

		Mockito.when(repo.save(Mockito.any())).thenReturn(filme);

		FilmeDTO response = service.adicionar(filmeDTO);

		assertNotNull(response);

		assertEquals(filmeDTO.getClass(), response.getClass());

		Assertions.assertEquals(ID, filmeDTO.getId());
		Assertions.assertEquals(NOME, filmeDTO.getNome());
		Assertions.assertEquals(CATEGORIA, filmeDTO.getCategoria());

	}

	@Test
	void deletarFilmePeloId() {
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);
		Mockito.doNothing().when(repo).deleteById(Mockito.anyLong());
		service.deletar(ID);

		Mockito.verify(repo, times(1)).deleteById(Mockito.anyLong());

	}

	@Test
	private void atualizarFilmeComSucesso() {
		Mockito.when(repo.save(Mockito.any())).thenReturn(filmeDTO);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);

		FilmeDTO response = service.atualizar(ID, filmeDTO);

		assertNotNull(response);

		assertEquals(filmeDTO.getClass(), response.getClass());
		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(CATEGORIA, filme.getCategoria());

	}

	private void startFilme() {
		filme = new Filme(ID, NOME, CATEGORIA);
		filmeDTO = new FilmeDTO(ID, NOME, CATEGORIA);

		optionalFilme = Optional.of(new Filme(ID, NOME, CATEGORIA));
	}

}
