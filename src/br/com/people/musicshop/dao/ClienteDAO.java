package br.com.people.musicshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.people.musicshop.entity.Cliente;

public class ClienteDAO {
	
	private Connection connection;

	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}

	public Integer inserir(Cliente cliente) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into cliente(nome,endereco) values (?,?)");
			prepareStatement.setString(1, cliente.getNome());
			prepareStatement.setString(2, cliente.getEndereco());
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
	
	public Cliente carregar(Integer id) {
		try {
			String sql = "select * from cliente where cliente_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			Cliente cliente = null;
			if (resultSet.next()) {
				cliente = new Cliente();
				cliente.setId(resultSet.getInt("cliente_id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEndereco(resultSet.getString("endereco"));
			}
			stmt.close();
			
			return cliente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void excluir(Integer id) throws Exception {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(
					"delete from cliente where cliente_id = ?");
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
	
	public List<Cliente> listarTodos() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			String sql = "select * from cliente order by nome";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("cliente_id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEndereco(resultSet.getString("endereco"));
				clientes.add(cliente);
			}
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Cliente>();
		}
	}
	
	public void alterar(Cliente cliente) throws Exception {
		try {
			String sqlUpdate = "update cliente set nome = ?, endereco = ? "
					+ "where cliente_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setInt(3, cliente.getId());
			if (stmt.executeUpdate() == 0) {
				throw new Exception("Nenhum registro alterado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
