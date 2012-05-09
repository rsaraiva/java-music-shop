package br.com.people.musicshop.controller;

import java.sql.Connection;

import br.com.people.musicshop.dao.ClienteDAO;
import br.com.people.musicshop.entity.Cliente;
import br.com.people.musicshop.factory.ConnectionFactory;

public class ClienteController {
	
	private Connection connection;
	private ClienteDAO clienteDAO;

	public void listar() {
		connection = new ConnectionFactory().getConnection();
		clienteDAO = new ClienteDAO(connection);
		for (Cliente cliente : clienteDAO.listarTodos()) {
			System.out.println(" " + cliente);
		}
		System.out.println();
		clienteDAO.fecharConexao();
	}
	
	public void inserir(String nome, String endereco) {
		connection = new ConnectionFactory().getConnection();
		clienteDAO = new ClienteDAO(connection);
		clienteDAO.inserir(new Cliente(nome, endereco));
		clienteDAO.fecharConexao();
	}
	
	public void excluir(Integer id) throws Exception {
		connection = new ConnectionFactory().getConnection();
		clienteDAO = new ClienteDAO(connection);
		clienteDAO.excluir(id);
		clienteDAO.fecharConexao();
	}
	
	public void alterar(Cliente cliente) throws Exception {
		connection = new ConnectionFactory().getConnection();
		clienteDAO = new ClienteDAO(connection);
		Cliente cliente_ = clienteDAO.carregar(cliente.getId());
		cliente_.setNome(cliente.getNome());
		cliente_.setEndereco(cliente.getEndereco());
		clienteDAO.alterar(cliente_);
		clienteDAO.fecharConexao();
	}
	
	public Cliente carregar(Integer id) {
		connection = new ConnectionFactory().getConnection();
		clienteDAO = new ClienteDAO(connection);
		Cliente cliente = clienteDAO.carregar(id);
		clienteDAO.fecharConexao();
		return cliente;
	}
}
