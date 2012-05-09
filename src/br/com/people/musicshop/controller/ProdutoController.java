package br.com.people.musicshop.controller;

import java.sql.Connection;

import br.com.people.musicshop.dao.ProdutoDAO;
import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.entity.Produto;
import br.com.people.musicshop.factory.ConnectionFactory;

public class ProdutoController {
	
	private Connection connection;
	private ProdutoDAO produtoDAO;

	public void listar() {
		connection = new ConnectionFactory().getConnection();
		produtoDAO = new ProdutoDAO(connection);
		for (Produto produto : produtoDAO.listarTodos()) {
			System.out.println(" " + produto);
		}
		System.out.println();
		produtoDAO.fecharConexao();
	}
	
	public void inserir(Categoria categoria, String nome, Float valor) {
		connection = new ConnectionFactory().getConnection();
		produtoDAO = new ProdutoDAO(connection);
		produtoDAO.inserir(new Produto(categoria, nome, valor));
		produtoDAO.fecharConexao();
	}
	
	public void excluir(Integer id) throws Exception {
		connection = new ConnectionFactory().getConnection();
		produtoDAO = new ProdutoDAO(connection);
		produtoDAO.excluir(id);
		produtoDAO.fecharConexao();
	}
	
	public void alterar(Produto produto) throws Exception {
		connection = new ConnectionFactory().getConnection();
		produtoDAO = new ProdutoDAO(connection);
		Produto produto_ = produtoDAO.carregar(produto.getId());
		produto_.setNome(produto.getNome());
		produto_.setValor(produto.getValor());
		produto_.setCategoria(produto.getCategoria());
		produtoDAO.alterar(produto_);
		produtoDAO.fecharConexao();
	}
	
	public Produto carregar(Integer id) {
		connection = new ConnectionFactory().getConnection();
		produtoDAO = new ProdutoDAO(connection);
		Produto produto = produtoDAO.carregar(id);
		produtoDAO.fecharConexao();
		return produto;
	}
}
