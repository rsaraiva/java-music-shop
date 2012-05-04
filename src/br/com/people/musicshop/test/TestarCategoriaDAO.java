package br.com.people.musicshop.test;

import java.sql.Connection;
import java.util.List;

import br.com.people.musicshop.dao.CategoriaDAO;
import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarCategoriaDAO {
	
	private static Integer id;

	public static void main(String[] args) {
		inserir();
		carregar();
		alterar();
		listarTodos();
		excluir();
	}
	
	private static void inserir() {
		System.out.println("inserindo...");
		Categoria novaCategoria = new Categoria();
		novaCategoria.setNome("Pedais de Guitarra");
		
		Connection connection = new ConnectionFactory().getConnection();
		CategoriaDAO dao = new CategoriaDAO(connection);
		id = dao.inserir(novaCategoria);
		dao.fecharConexao();
		System.out.println("inserido com o id: " + id);
	}
	
	private static void carregar() {
		System.out.println("carregando o id " + id);
		Connection connection = new ConnectionFactory().getConnection();
		CategoriaDAO dao = new CategoriaDAO(connection);
		Categoria categoriaCarregada = dao.carregar(id);
		dao.fecharConexao();
		System.out.println("carregou a categoria " + categoriaCarregada.getNome());
	}
	
	private static void alterar() {
		System.out.println("alterando...");
		CategoriaDAO dao = new CategoriaDAO(new ConnectionFactory().getConnection());
		// carregar
		Categoria categoriaCarregada = dao.carregar(id);
		categoriaCarregada.setNome("Pedais Overdrive");
		// alterar de verdade
		try {
			dao.alterar(categoriaCarregada);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dao.fecharConexao();
	}
	
	private static void listarTodos() {
		System.out.println("listando todas...");
		CategoriaDAO dao = new CategoriaDAO(new ConnectionFactory().getConnection());
		List<Categoria> categorias = dao.listarTodos();
		if (categorias.isEmpty()) {
			System.out.println("nenhuma categoria encontrada");
			dao.fecharConexao();
			return;
		}
		for (Categoria categoria : dao.listarTodos()) {
			System.out.println(categoria.getNome());
		}
		dao.fecharConexao();
	}
	
	private static void excluir() {
		System.out.println("excluindo categoria id " + id);
		Connection connection = new ConnectionFactory().getConnection();
		CategoriaDAO dao = new CategoriaDAO(connection);
		try {
			dao.excluir(id);
			System.out.println("categoria excluída: " + id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		dao.fecharConexao();
	}
}
