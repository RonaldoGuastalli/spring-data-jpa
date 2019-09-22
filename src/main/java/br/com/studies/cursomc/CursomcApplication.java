package br.com.studies.cursomc;

import br.com.studies.cursomc.domanin.*;
import br.com.studies.cursomc.domanin.enums.EstadoPagamento;
import br.com.studies.cursomc.domanin.enums.TipoCliente;
import br.com.studies.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = Categoria.builder().id(null).nome("Informática").build();
        Categoria cat2 = Categoria.builder().id(null).nome("Escritório").build();
        Categoria cat3 = Categoria.builder().id(null).nome("Cama mesa e banho").build();
        Categoria cat4 = Categoria.builder().id(null).nome("Eletrônicos").build();
        Categoria cat5 = Categoria.builder().id(null).nome("Jardinagem").build();
        Categoria cat6 = Categoria.builder().id(null).nome("Decoração").build();
        Categoria cat7 = Categoria.builder().id(null).nome("Perfumaria").build();

        Produto p1 = Produto.builder().id(null).nome("Computador").preco(2000.00).build();
        Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.00).build();
        Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.00).build();

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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

        Cliente cli1 = Cliente.builder().id(null).nome("Maria Silva").email("maria@gmail.com").cpfOuCnpj("36378912377").tipo(TipoCliente.PESSOA_FISICA.getCod()).build();

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = Endereco.builder().id(null).logradouro("Rua Flores").numero("300").complemento("Apto 303").bairro("Jardim").cep("38220834").cliente(cli1).cidade(cid1).build();
        Endereco e2 = Endereco.builder().id(null).logradouro("Avenida Matos").numero("105").complemento("sala 800").bairro("Centro").cep("38777012").cliente(cli1).cidade(cid2).build();
        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = Pedido.builder().id(null).instante(sdf.parse("30/09/2017 10:32")).cliente(cli1).enderecoDeEntrega(e1).build();
        Pedido ped2 = Pedido.builder().id(null).instante(sdf.parse("10/10/2017 19:35")).cliente(cli1).enderecoDeEntrega(e2).build();

        Pagamento pagto1 = PagamentoComCartao.pagamentoComCartaoBuilder().id(null).estado(EstadoPagamento.QUITADO.getCod()).pedido(ped1).numeroDeParcelas(6).build();
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = PagamentoComBoleto.pagamentoComBoletoBuilder().id(null).estado(EstadoPagamento.PENDENTE.getCod()).pedido(ped2).dataVencimento(sdf.parse("20/10/2017 00:00")).dataPagamento(null).build();
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));


        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));



    }
}
