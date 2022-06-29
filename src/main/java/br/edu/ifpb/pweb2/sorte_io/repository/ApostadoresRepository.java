package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;

public interface ApostadoresRepository extends JpaRepository<Apostador, Long> {

	@Query("SELECT A FROM Apostador A JOIN FETCH A.user U WHERE U.username = :USERNAME")
	Optional<Apostador> findByUser(@Param("USERNAME") String username);

}
