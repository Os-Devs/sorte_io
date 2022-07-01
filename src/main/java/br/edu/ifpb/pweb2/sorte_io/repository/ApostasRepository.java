package br.edu.ifpb.pweb2.sorte_io.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.pweb2.sorte_io.model.Aposta;

public interface ApostasRepository extends JpaRepository<Aposta, Integer> {

    @Query("SELECT A FROM Aposta A JOIN FETCH A.apostador A2 JOIN FETCH A2.user U WHERE U.username = :USERNAME")
    Optional<List<Aposta>> findByForUser(@Param("USERNAME") String username);

    @Query("SELECT A FROM Aposta A JOIN FETCH A.apostador A2 JOIN FETCH A2.user U WHERE U.username = :USERNAME AND A.favorito = 1")
    Optional<List<Aposta>> findByFavoritoTrueForUser(@Param("USERNAME") String username);

}
