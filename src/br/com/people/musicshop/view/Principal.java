package br.com.people.musicshop.view;

import java.io.Console;
import java.sql.SQLException;
import java.util.Date;

import br.com.people.musicshop.controller.CategoriaController;
import br.com.people.musicshop.controller.ClienteController;
import br.com.people.musicshop.controller.ProdutoController;
import br.com.people.musicshop.controller.VendaController;
import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.entity.Cliente;
import br.com.people.musicshop.entity.Produto;
import br.com.people.musicshop.entity.Venda;

public class Principal {

	// console para capturar entrada de dados
	static Console console = System.console();
	
	// controllers com metodos de controle da view
	static CategoriaController categoriaController = new CategoriaController();
	static ClienteController clienteController = new ClienteController();
	static ProdutoController produtoController = new ProdutoController();
	static VendaController vendaController = new VendaController();
	
	// metodo principal
	public static void main(String[] args) throws SQLException {
		// exibe o menu na inicializacao do programa
		exibirMenu();
		
		// loop que espera um comando e o processa
		while(true) {
			label(" Digite um comando: ");
			executarComando(console.readLine());
		}
	}
	
	// metodo que recebe o comando digitado pelo usuÃ¡rio e aciona o controller
	public static void executarComando(String comando) {
		switch (Integer.parseInt(comando)) {
		case 0: // reexibe o menu na tela
			exibirMenu();
			break;
		case 9: // sai do programa
			mensagem("AtÃ© logo!");
			System.exit(0);
			break;
		
		// ===== CATEGORIAS =====
			
		case 11: // lista categorias
			mensagem("Categorias cadastradas:");
			categoriaController.listar();
			break;
		case 12: // espera um nome e insere uma nova categoria
			label("Nome: ");
			categoriaController.inserir(console.readLine());
			mensagem("Categoria inserida com sucesso!");
			break;
		case 13: // espera um codigo e chama o metodo de exclusao
			try {
				label("CÃ³digo: ");
				categoriaController.excluir(Integer.parseInt(console.readLine()));
				mensagem("Categoria excluida com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
		case 14: // espera um codigo e chama o metodo alterar do controller
			try {
				label("CÃ³digo: ");
				Categoria categoria = categoriaController.carregar(
						Integer.parseInt(console.readLine()));
				label("Novo nome ("+categoria.getNome()+"): ");
				categoria.setNome(console.readLine());
				categoriaController.alterar(categoria);
				mensagem("Categoria alterada com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
		
		
		// ===== CLIENTES =====
		
		case 21: // lista clientes
			mensagem("Clientes cadastrados:");
			clienteController.listar();
			break;
		case 22: // inserir cliente
			{
				label("Nome: ");
				String nome = console.readLine();
				label("Endereco: ");
				String endereco = console.readLine();
				clienteController.inserir(nome, endereco);
				mensagem("Cliente cadastrado com sucesso!");
			}
			break;
		case 23: // excluir cliente
			try {
				label("Codigo: ");
				clienteController.excluir(Integer.parseInt(console.readLine()));
				mensagem("Categoria excluida com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
		case 24: // alterar cliente
			try {
				label("Codigo: ");
				Cliente cliente = clienteController.carregar(Integer.parseInt(console.readLine()));
				label("Novo nome ("+cliente.getNome()+"): ");
				String nome = console.readLine();
				if (!nome.isEmpty()) {
					cliente.setNome(nome);
				}
				label("Novo endereço ("+cliente.getEndereco()+"): ");
				String endereco = console.readLine();
				if (!endereco.isEmpty()) {
					cliente.setEndereco(endereco);
				}
				clienteController.alterar(cliente);
				mensagem("Cliente alterado com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
			
			
		// ===== PRODUTOS =====

		case 31: // lista produtos
			mensagem("Produtos cadastrados:");
			produtoController.listar();
			break;
		case 32: // inserir produto
			{
				mensagem("Categorias cadastradas:");
				categoriaController.listar();
				
				label("Codigo da Categoria: ");
				String codCategoria = console.readLine();
				label("Nome: ");
				String nome = console.readLine();
				label("Valor: ");
				String valor = console.readLine();
				produtoController.inserir(
						new Categoria(Integer.parseInt(codCategoria)), 
						nome, 
						Float.parseFloat(valor));
				mensagem("Produto cadastrado com sucesso!");
			}
			break;
		case 33: // excluir produto
			try {
				label("CÃ³digo: ");
				produtoController.excluir(Integer.parseInt(console.readLine()));
				mensagem("Produto excluido com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
		case 34: // alterar produto
			try {
				label("CÃ³digo do Produto: ");
				Produto produto = produtoController.carregar(Integer.parseInt(console.readLine()));
				
				mensagem("Categorias cadastradas:");
				categoriaController.listar();
				
				label("Novo CÃ³digo da Categoria (" + produto.getCategoria().getId() + "): ");
				String codCategoria = console.readLine();
				if (!codCategoria.isEmpty()) {
					produto.setCategoria(new Categoria(Integer.parseInt(codCategoria)));
				}
				label("Novo Nome (" + produto.getNome() + "): ");
				String nome = console.readLine();
				if (!nome.isEmpty()) {
					produto.setNome(nome);
				}
				label("Novo Valor (" + produto.getValor() + "): ");
				String valor = console.readLine();
				if (!valor.isEmpty()) {
					produto.setValor(Float.parseFloat(valor));
				}
				produtoController.alterar(produto);
				mensagem("Produto alterado com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
			
			// ===== VENDAS =====
			
		case 41: // efetuar venda
			
			try {
				mensagem("Clientes cadastrados: ");
				clienteController.listar();
				
				label("Digite o codigo do cliente: ");
				String codCliente = console.readLine();
				
				mensagem("Produtos cadastrados: ");
				produtoController.listar();
				
				label("Digite o codigo do produto: ");
				String codProduto = console.readLine();
				
//				Cliente cliente = new Cliente();
//				cliente.setId(Integer.parseInt(codCliente));
//				
//				Produto produto = new Produto();
//				produto.setId(Integer.parseInt(codProduto));
//				
//				Venda venda = new Venda();
//				venda.setCliente(cliente);
//				venda.setProduto(produto);
//				venda.setData(new Date());
				
				Venda venda = new Venda(
						new Cliente(Integer.parseInt(codCliente)),
						new Produto(Integer.parseInt(codProduto)),
						new Date());
				
				vendaController.efetuarVenda(venda);
				
				mensagem("Venda efetuada com sucesso!");
				
			} catch (Exception e) {
				e.printStackTrace();
				mensagem(e.getMessage());
			}
			
			break;
			
		case 42: // listar vendas
			
			mensagem("Vendas Realizadas:");
			vendaController.listar();
			
			break;
			
		case 43: // listar vendas por cliente
			
			mensagem("Clientes cadastrados: ");
			clienteController.listar();
			
			label("Informe o codigo do cliente: ");
			String codCliente = console.readLine();
			
			mensagem("Vendas Realizadas:");
			vendaController.listarPorCliente(
					new Cliente(Integer.parseInt(codCliente)));
			
			break;
			
		default:
			mensagem("Opcao invalida!");
			break;
		}
	}
	
	// metodo que exibe o menu
	private static void exibirMenu() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("========= Java Music Shop 1.0 =========");
		System.out.println("=======================================");
		System.out.println();
		System.out.println(" Menu de Comandos:");
		System.out.println();
		System.out.println(" Categorias: (11) Listar | (12) Criar | (13) Excluir | (14) Alterar");
		System.out.println(" Clientes:   (21) Listar | (22) Criar | (23) Excluir | (24) Alterar");
		System.out.println(" Produtos:   (31) Listar | (32) Criar | (33) Excluir | (34) Alterar");
		System.out.println(" Vendas:     (41) Efetuar Venda | (42) Listar Vendas | (43) Vendas por Cliente");
		System.out.println();
		System.out.println(" (0) Exibir o menu");
		System.out.println(" (9) Sair");
		System.out.println();
	}
	
	// metodo que exibe uma mensagem com espacamentos
	private static void mensagem(String texto) {
		System.out.println();
		System.out.println(" " + texto);
		System.out.println();
	}
	
	// metodo que exibe uma mensagem sem quebrar linha
	private static void label(String texto) {
		System.out.println();
		System.out.print(" " + texto);
	}
}