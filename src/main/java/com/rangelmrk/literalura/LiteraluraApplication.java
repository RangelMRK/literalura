package com.rangelmrk.literalura;

import com.rangelmrk.literalura.main.Main;
import com.rangelmrk.literalura.repository.AutorRepository;
import com.rangelmrk.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository repositorioAutor;

	@Autowired
	private LivroRepository repositorioLivro;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositorioAutor,repositorioLivro);
		main.exibeMenu();
	}
}
