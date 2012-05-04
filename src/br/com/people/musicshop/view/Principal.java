package br.com.people.musicshop.view;

import java.io.Console;

import br.com.people.musicshop.dao.CategoriaDAO;
import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.factory.ConnectionFactory;

public class Principal {

	public static void main(String[] args) {
		Console console = System.console();
		CategoriaDAO dao = new CategoriaDAO(new ConnectionFactory().getConnection());
		boolean continua = true;
		while(continua) {
			System.out.print("Digite um comando: ");
			String comando = console.readLine();
			for (Categoria categoria: dao.listarTodos()) {
				System.out.println(categoria);
			}
			continua = false;
		}
		dao.fecharConexao();
	}
}
