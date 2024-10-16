package com.serratec.empresatec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.empresatec.dto.CandidatoDto;
import com.serratec.empresatec.model.StatusCurriculo;
import com.serratec.empresatec.model.VagaDesejada;
import com.serratec.empresatec.service.CandidatoService;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	@Autowired
	private CandidatoService servico;

	@GetMapping
	public List<CandidatoDto> buscarTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> buscarId(@PathVariable Long id) {
		Optional<CandidatoDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}

	@GetMapping("/status/{statusCurriculo}")
	public List<CandidatoDto> selecionarPorStatus(@PathVariable StatusCurriculo statusCurriculo) {
		return servico.obterPorStatusCurriculo(statusCurriculo);
	}

	@GetMapping("/vaga/{vagaDesejada}")
	public List<CandidatoDto> selecionarPorVagaDesejada(@PathVariable VagaDesejada vagaDesejada) {
		return servico.obterPorVagaDesejada(vagaDesejada);
	}

	@GetMapping("/escolaridade/{escolaridade}")
	public List<CandidatoDto> selecionarPorEscolaridade(@PathVariable Escolaridade escolaridade) {
		return servico.obterPorEscolaridade(escolaridade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto adicionarCandidato(@RequestBody CandidatoDto dto) {
		return servico.salvarCandidato(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> modificarCandidato(@PathVariable Long id, @RequestBody CandidatoDto dto) {
		Optional<CandidatoDto> candidatoAlterado = servico.alterarCandidato(id, dto);
		if (!candidatoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatoAlterado.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCandidato(@PathVariable Long id) {
		if (!servico.apagarCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
