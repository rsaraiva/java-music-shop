package br.com.people.musicshop.controller;

import java.sql.Connection;

import br.com.people.musicshop.dao.VendaDAO;
import br.com.people.musicshop.entity.Cliente;
import br.com.people.musicshop.entity.Venda;
import br.com.people.musicshop.factory.ConnectionFactory;

public class VendaController {

	private Connection connection;
	private VendaDAO vendaDAO;
	
	public void efetuarVenda(Venda venda) {
		connection = new ConnectionFactory().getConnection();
		vendaDAO = new VendaDAO(connection);
		vendaDAO.inserir(venda);
		vendaDAO.fecharConexao();
	}
	
	public void listar() {
		connection = new ConnectionFactory().getConnection();
		vendaDAO = new VendaDAO(connection);
		for (Venda venda : vendaDAO.listarTodos()) {
			System.out.println(venda);
		}
		vendaDAO.fecharConexao();
	}
	
	public void listarPorCliente(Cliente cliente) {
		connection = new ConnectionFactory().getConnection();
		vendaDAO = new VendaDAO(connection);
		for (Venda venda : vendaDAO.listarPorCliente(cliente)) {
			System.out.println(venda);
		}
		vendaDAO.fecharConexao();
	}
}
