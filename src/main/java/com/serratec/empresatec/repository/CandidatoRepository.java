package com.serratec.empresatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.empresatec.model.Candidato;
import com.serratec.empresatec.model.StatusCurriculo;
import com.serratec.empresatec.model.VagaDesejada;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	List<Candidato> findByStatusCurriculo(StatusCurriculo statusCurriculo);
	
	List<Candidato> findByVagaDesejada(VagaDesejada vagaDesejada);
}
