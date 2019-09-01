package br.com.studies.cursomc;

import br.com.studies.cursomc.domanin.Categoria;
import br.com.studies.cursomc.domanin.Cidade;
import br.com.studies.cursomc.domanin.Estado;
import br.com.studies.cursomc.domanin.Produto;
import br.com.studies.cursomc.repository.CategoriaRepository;
import br.com.studies.cursomc.repository.CidadeRepository;
import br.com.studies.cursomc.repository.EstadoRepository;
import br.com.studies.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;


    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = Categoria.builder().id(null).nome("Informática").build();
        Categoria cat2 = Categoria.builder().id(null).nome("Escritório").build();

        Produto p1 = Produto.builder().id(null).nome("Computador").preco(2000.00).build();
        Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.00).build();
        Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.00).build();

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = Estado.builder().id(null).nome("Minas Geraris").build();
        Estado est2 = Estado.builder().id(null).nome("São Paulo").build();

        Cidade cid1 = Cidade.builder().id(null).nome("Uberlândia").estado(est1).build();
        Cidade cid2 = Cidade.builder().id(null).nome("São Paulo").estado(est2).build();
        Cidade cid3 = Cidade.builder().id(null).nome("Campinas").estado(est2).build();

        est1.getCidades().addAll(List.of(cid1));
        est2.getCidades().addAll(List.of(cid2, cid3));

        estadoRepository.saveAll(List.of(est1, est2));
        cidadeRepository.saveAll(List.of(cid1, cid2, cid3));

    }
}
