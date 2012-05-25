package br.com.people.musicshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.people.musicshop.entity.Cliente;
import br.com.people.musicshop.entity.Venda;

public class VendaDAO {
	
	private Connection connection;

	public VendaDAO(Connection connection) {
		this.connection = connection;
	}

	public Integer inserir(Venda venda) {
		try {
			java.sql.Date dataSQL = new java.sql.Date(venda.getData().getTime());
			
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into venda(cliente_id, produto_id, " +
							"data) values (?,?,?)");
			prepareStatement.setInt(1, venda.getCliente().getId());
			prepareStatement.setInt(2, venda.getProduto().getId());
			prepareStatement.setDate(3, dataSQL);
			prepareStatement.executeUpdate();
			// get last id
			Integer id = null;
			ResultSet resultSet = prepareStatement.executeQuery("select last_insert_id()");
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			prepareStatement.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Venda carregar(Integer id) {
		
		ProdutoDAO produtoDAO = new ProdutoDAO(connection);
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		
		try {
			String sql = "select * from venda where venda_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			Venda venda = null;
			if (resultSet.next()) {
				venda = new Venda();
				venda.setId(resultSet.getInt("produto_id"));
				venda.setCliente(clienteDAO.carregar(resultSet.getInt("cliente_id")));
				venda.setProduto(produtoDAO.carregar(resultSet.getInt("produto_id")));
				venda.setData(resultSet.getDate("data"));
			}
			stmt.close();
			
			return venda;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void excluir(Integer id) throws Exception {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(
					"delete from venda where venda_id = ?");
			prepareStatement.setInt(1, id);
			if (prepareStatement.executeUpdate() == 0) {
				throw new Exception("Nenhum registro encontrado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Venda> listarTodos() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO(connection);
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		
		try {
			List<Venda> vendas = new ArrayList<Venda>();
			String sql = "select * from venda order by data";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Venda venda = new Venda();
				venda.setId(resultSet.getInt("produto_id"));
				venda.setCliente(clienteDAO.carregar(resultSet.getInt("cliente_id")));
				venda.setProduto(produtoDAO.carregar(resultSet.getInt("produto_id")));
				venda.setData(resultSet.getDate("data"));
				vendas.add(venda);
			}
			stmt.close();
			return vendas;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Venda>();
		}
	}
	
	public void alterar(Venda venda) throws Exception {
		try {
			String sqlUpdate = "update venda set cliente_id = ?, produto_id = ?, data = ? "
					+ "where venda_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
			stmt.setInt(1, venda.getCliente().getId());
			stmt.setInt(2, venda.getProduto().getId());
			stmt.setDate(3, (java.sql.Date) venda.getData());
			stmt.setInt(4, venda.getId());
			if (stmt.executeUpdate() == 0) {
				throw new Exception("Nenhum registro alterado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Venda> listarPorCliente(Cliente cliente) { //
		ProdutoDAO produtoDAO = new ProdutoDAO(connection);
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		try {
			List<Venda> vendas = new ArrayList<Venda>();
			String sql = "select * from venda where cliente_id = ? order by data"; //
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getId()); //
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Venda venda = new Venda();
				venda.setId(resultSet.getInt("produto_id"));
				venda.setCliente(clienteDAO.carregar(resultSet.getInt("cliente_id")));
				venda.setProduto(produtoDAO.carregar(resultSet.getInt("produto_id")));
				venda.setData(resultSet.getDate("data"));
				vendas.add(venda);
			}
			stmt.close();
			return vendas;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Venda>();
		}
	}
	
	public void fecharConexao() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}