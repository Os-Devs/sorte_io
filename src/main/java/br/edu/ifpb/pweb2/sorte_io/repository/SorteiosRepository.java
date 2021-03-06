package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

public interface SorteiosRepository extends JpaRepository<Sorteio, Integer> {

	@Query("SELECT S FROM Sorteio S WHERE S.realizado = 0")
	Optional<List<Sorteio>> findBySorteiosNaoRealizados();

	@Query("SELECT S FROM Sorteio S WHERE S.realizado = 1")
	Optional<List<Sorteio>> findBySorteiosRealizados();

	@Query("SELECT S FROM Sorteio S JOIN FETCH S.criadoPor C JOIN FETCH C.user U WHERE U.username = :USERNAME")
    Optional<List<Sorteio>> findBySorteiosForUser(@Param("USERNAME") String username);

	@Query("SELECT S FROM Sorteio S JOIN FETCH S.criadoPor C JOIN FETCH C.user U WHERE U.username = :USERNAME AND S.realizado = 0")
	Optional<List<Sorteio>> findBySorteioAbertoForUser(@Param("USERNAME") String username);
	
}
