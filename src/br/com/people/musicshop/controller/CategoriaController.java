package br.com.people.musicshop.controller;

import java.sql.Connection;

import br.com.people.musicshop.dao.CategoriaDAO;
import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.factory.ConnectionFactory;

public class CategoriaController {
	
	// o controller sera responsavel pela abertura e fechamento da conexao,
	// bem como instanciar o dao e chamar os metodos de acesso a dados
	
	// declara variavel connection e dao
	private Connection connection;
	private CategoriaDAO categoriaDAO;

	// abre nova conexao, instancia o dao, chama metodo de listagem e exibe todas as categorias retornadas
	public void listar() {
		connection = new ConnectionFactory().getConnection();
		categoriaDAO = new CategoriaDAO(connection);
		for (Categoria categoria : categoriaDAO.listarTodos()) {
			System.out.println(" " + categoria);
		}
		System.out.println();
		categoriaDAO.fecharConexao();
	}
	
	// abre nova conexao, instancia o dao, chama metodo inserir passando o nome informado
	public void inserir(String nome) {
		connection = new ConnectionFactory().getConnection();
		categoriaDAO = new CategoriaDAO(connection);
		categoriaDAO.inserir(new Categoria(nome));
		categoriaDAO.fecharConexao();
	}
	
	// abre nova conexao, instancia o dao, chama metodo excluir passando o id informado
	public void excluir(Integer id) throws Exception {
		connection = new ConnectionFactory().getConnection();
		categoriaDAO = new CategoriaDAO(connection);
		categoriaDAO.excluir(id);
		categoriaDAO.fecharConexao();
	}
	
	// abre nova conexao, instancia o dao
	public void alterar(Categoria categoria) throws Exception {
		connection = new ConnectionFactory().getConnection();
		categoriaDAO = new CategoriaDAO(connection);
		// chama metodos carregar do dao
		Categoria categoria_ = categoriaDAO.carregar(categoria.getId());
		// altera nome do objeto carregado com o nome do objeto informado
		categoria_.setNome(categoria.getNome());
		// aplica alteracao chamando o metodo alterar do dao
		categoriaDAO.alterar(categoria_);
		categoriaDAO.fecharConexao();
	}
	
	// abre nova conexao, instancia o dao, chama metodo carregar e retorna objeto retornado pelo dao
	public Categoria carregar(Integer id) {
		connection = new ConnectionFactory().getConnection();
		categoriaDAO = new CategoriaDAO(connection);
		Categoria categoria = categoriaDAO.carregar(id);
		categoriaDAO.fecharConexao();
		return categoria;
	}
}
