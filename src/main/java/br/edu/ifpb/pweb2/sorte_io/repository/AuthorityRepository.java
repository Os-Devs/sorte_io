package br.edu.ifpb.pweb2.sorte_io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.sorte_io.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    
}
