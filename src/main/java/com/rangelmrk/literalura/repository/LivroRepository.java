package com.rangelmrk.literalura.repository;

import com.rangelmrk.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String escolhaIdioma);

    List<Livro> findTop10ByOrderByDownloadsDesc();
}
