package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.User;

public interface ApostadoresRepository extends JpaRepository<Apostador, Long> {

	Optional<Apostador> findByUser(User user);
    
}
