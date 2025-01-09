package com.rangelmrk.literalura.main;

import com.rangelmrk.literalura.model.*;
import com.rangelmrk.literalura.repository.AutorRepository;
import com.rangelmrk.literalura.repository.LivroRepository;
import com.rangelmrk.literalura.service.ConsumoAPI;
import com.rangelmrk.literalura.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private AutorRepository repositorioAutor;
    private LivroRepository repositorioLivro;

    public Main(AutorRepository repositorioAutor, LivroRepository repositorioLivro){
        this.repositorioAutor = repositorioAutor;
        this.repositorioLivro = repositorioLivro;
    }


    public void exibeMenu(){


        String menu = """
                1 - Buscar Livro pelo título
                2 - Listar Livros Registrados
                3 - Listar Autores Registrados
                4 - Listar Autores vivos em um determinado ano
                5 - Listar Livros em um determinado Idioma
                                
                0 - Sair                                 
                """;



        int opcao = -1;
        while(opcao !=0) {
            System.out.println(menu);
            try{
                opcao = input.nextInt();
                input.nextLine();
                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            }catch (NumberFormatException e) {
                System.out.println("Entada inválida. Por favor, digite um número!");
            }
        }
    }

    private Optional<DadosLivro> getDadosLivro(){
        System.out.println("Digite o Título do Livro que deseja buscar: ");
        var nomeLivro = input.nextLine();

        try{
            var json = consumoAPI.obterDados(ENDERECO + nomeLivro.replace(" ", "+")
                    .toLowerCase());
            DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);

            if (dadosResposta != null && !dadosResposta.dadosLivro().isEmpty()) {
                return Optional.of(dadosResposta.dadosLivro().get(0));
            } else {
                System.out.println("Nenhum livro encontrado com o título: " + nomeLivro);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados do livro: " + e.getMessage());
        }

        return Optional.empty();
    }




    private void exibeInfoLivro(Livro livro){
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
        System.out.println("Idioma: " + String.join(", ", livro.getIdioma()));
        System.out.println("Downloads: " + livro.getDownloads());

    }

    private void exibeInfoAutor(Autor autor){
        System.out.println("Autor: " + autor.getNome());
        System.out.println("Ano de Nascimento: " + autor.getAnoNascimento());
        System.out.println("Ano de Falecimento: " + autor.getAnoFalecimento());

        List<String> livros = autor.getLivros().stream().sorted(Comparator.comparing(Livro::getTitulo))
                .map(Livro::getTitulo)
                .collect(Collectors.toList());

        System.out.println("Livros: " + livros);
    }


    private void buscarLivroPorTitulo() {
        Optional<DadosLivro> optionalDadosLivro = getDadosLivro();

        optionalDadosLivro.ifPresentOrElse(dadosLivro -> {
            if (dadosLivro.detalhesAutor().isEmpty()) {
                System.out.println("Erro: Nenhum autor encontrado para o livro.");
                return;
            }

            DadosAutor dadosAutor = dadosLivro.detalhesAutor().get(0);
            Autor autor = repositorioAutor.findByNomeAndAnoNascimento(
                    dadosAutor.nome(), dadosAutor.anoNascimento()
            ).orElseGet(() -> {
                Autor novoAutor = new Autor(dadosAutor);
                repositorioAutor.save(novoAutor);
                return novoAutor;
            });

            Livro livro = new Livro(dadosLivro);
            livro.setAutor(autor);
            repositorioLivro.save(livro);

            exibeInfoLivro(livro);
        }, () -> System.out.println("Livro não encontrado."));
    }

    private void listarLivrosRegistrados() {
        List<Livro> livrosRegistrados = repositorioLivro.findAll();

        livrosRegistrados.forEach(l -> exibeInfoLivro(l));

    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresRegistrados = repositorioAutor.findAll();
        autoresRegistrados.forEach(a -> exibeInfoAutor(a));

    }

    private void listarAutoresVivosAno() {
        System.out.println("Digite o ano que deseja buscar os autores");
        var anoBusca = input.nextInt();
        input.nextLine();

        List<Autor> vivoAno = repositorioAutor.vivosEmAno(anoBusca);

        if(vivoAno.isEmpty()) {
            System.out.println("Não há autores no banco de Dados que estavam vivos em " + anoBusca);
        } else {
            vivoAno.forEach(a -> exibeInfoAutor(a));
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                \nInsira o idioma para realizar a Busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                \n 
                """ );

        System.out.println("Digite a sigla do idioma que deseja exibir os livros");
        var escolhaIdioma = input.nextLine();

        List<Livro> listaLivros = repositorioLivro.findByIdioma(escolhaIdioma);

        if(listaLivros.isEmpty()){
            System.out.println("Não foram encontrados Livros para este idioma: " + escolhaIdioma);
        } else {
            listaLivros.forEach(l -> exibeInfoLivro(l));
        }

    }
}
