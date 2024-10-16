package com.serratec.empresatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.empresatec.dto.CandidatoDto;
import com.serratec.empresatec.model.Candidato;
import com.serratec.empresatec.model.StatusCurriculo;
import com.serratec.empresatec.model.VagaDesejada;
import com.serratec.empresatec.repository.CandidatoRepository;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository repositorio;

	public List<CandidatoDto> obterTodos() {
		return repositorio.findAll().stream().map(p -> CandidatoDto.toDto(p)).toList();
	}

	public Optional<CandidatoDto> obterPorId(Long id) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(repositorio.findById(id).get()));
	}

	public List<CandidatoDto> obterPorVagaDesejada(VagaDesejada vagaDesejada) {
		List<Candidato> candidatos = repositorio.findByVagaDesejada(vagaDesejada);
		return candidatos.stream().map(p -> CandidatoDto.toDto(p)).toList();
	}

	public List<CandidatoDto> obterPorStatusCurriculo(StatusCurriculo statusCurriculo) {
		List<Candidato> candidatos = repositorio.findByStatusCurriculo(statusCurriculo);
		return candidatos.stream().map(p -> CandidatoDto.toDto(p)).toList();
	}
	
	public List<CandidatoDto> obterPorEscolaridade(Escolaridade escolaridade){
		List<Candidato> candidatos = repositorio.findByEscolaridade(escolaridade);
		return candidatos.stream().map(p -> CandidatoDto.toDto(p)).toList();
	}
	
	public CandidatoDto salvarCandidato(CandidatoDto dto) {
		return CandidatoDto.toDto(repositorio.save(dto.toEntity()));
	}

	public boolean apagarCandidato(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}

	public Optional<CandidatoDto> alterarCandidato(Long id, CandidatoDto dto) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Candidato candidatoEntity = dto.toEntity();
		candidatoEntity.setId(id);
		repositorio.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}

}
