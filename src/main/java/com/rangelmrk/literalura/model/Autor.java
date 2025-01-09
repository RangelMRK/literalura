package com.rangelmrk.literalura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor){
        this.nome = formatarNome(dadosAutor.nome());
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoMorte();
    }

    private String formatarNome(String nome) {
        if (nome.contains(",")) {
            String[] partes = nome.split(",", 2);
            return partes[1].trim() + " " + partes[0].trim();
        }
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }
}
