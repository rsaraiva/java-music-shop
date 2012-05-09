package br.com.people.musicshop.view;

import java.io.Console;
import java.sql.SQLException;

import br.com.people.musicshop.controller.CategoriaController;
import br.com.people.musicshop.entity.Categoria;

public class Principal {

	// console para capturar entrada de dados
	static Console console = System.console();
	
	// controllers com metodos de controle da view
	static CategoriaController categoriaController = new CategoriaController();
	
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
	
	// metodo que recebe o comando digitado pelo usuário e aciona o controller
	public static void executarComando(String comando) {
		switch (Integer.parseInt(comando)) {
		case 0: // reexibe o menu na tela
			exibirMenu();
			break;
		case 9: // sai do programa
			mensagem("Até logo!");
			System.exit(0);
			break;
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
				label("Código: ");
				categoriaController.excluir(Integer.parseInt(console.readLine()));
				mensagem("Categoria excluida com sucesso!");
			} catch (Exception e) {
				mensagem(e.getMessage());
			}
			break;
		case 14: // espera um codigo e chama o metodo alterar do controller
			try {
				label("Código: ");
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
		System.out.println(" Vendas:     (41) Efetuar Venda");
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