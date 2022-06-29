package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

public interface SorteiosRepository extends JpaRepository<Sorteio, Integer> {

	@Query("SELECT S FROM Sorteio S WHERE S.dtRealizacao > CURRENT_TIMESTAMP")
	Optional<List<Sorteio>> findBySorteiosNaoRealizados();
}
