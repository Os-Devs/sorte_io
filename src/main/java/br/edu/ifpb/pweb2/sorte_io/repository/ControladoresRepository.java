package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

public interface ControladoresRepository extends JpaRepository<Controlador, Integer> {

    @Query("SELECT C FROM Controlador C JOIN FETCH C.user U WHERE U.username = :USERNAME")
	Optional<Controlador> findByUser(@Param("USERNAME") String username);

    
}
