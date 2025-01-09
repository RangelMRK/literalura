package com.rangelmrk.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("authors") List<DadosAutor> detalhesAutor,
                         @JsonAlias("download_count") Integer downloads) {
}
