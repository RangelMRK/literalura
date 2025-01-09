package com.rangelmrk.literalura.repository;

import com.rangelmrk.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeAndAnoNascimento(String nome, Integer integer);

    @Query("SELECT a FROM Autor a WHERE :anoBusca BETWEEN a.anoNascimento AND a.anoFalecimento")
    List<Autor> vivosEmAno(Integer anoBusca);
}
