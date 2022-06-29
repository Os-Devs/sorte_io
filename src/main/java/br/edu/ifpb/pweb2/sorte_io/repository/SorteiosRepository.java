package br.edu.ifpb.pweb2.sorte_io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import java.util.List;

public interface SorteiosRepository extends JpaRepository<Sorteio, Long> {
	Sorteio findById(Integer id);
}
