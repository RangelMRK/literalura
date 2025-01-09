package com.rangelmrk.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Livro(){}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas().get(0);
        this.downloads = dadosLivro.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return  "Título: " + titulo +
                "Autor: " + autor.getNome() +
                "Idioma: " + idioma +
                "Número de Downloads: " + downloads;
    }
}
