package br.com.people.musicshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.factory.ConnectionFactory;

public class CategoriaDAO {
	
	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public Integer inserir(Categoria categoria) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into categoria(nome) values (?)");
			prepareStatement.setString(1, categoria.getNome());
			prepareStatement.executeUpdate();
			// get last id
			Integer id = null;
			ResultSet resultSet = prepareStatement.executeQuery(
					"select last_insert_id()");
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
	
	public Categoria carregar(Integer id) {
		try {
			String sql = "select * from categoria where categoria_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			Categoria categoria = null;
			if (resultSet.next()) {
				categoria = new Categoria();
				categoria.setId(resultSet.getInt("categoria_id"));
				categoria.setNome(resultSet.getString("nome"));
			}
			stmt.close();
			
			return categoria;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void excluir(Integer id) throws Exception {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(
					"delete from categoria where categoria_id = ?");
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
	
	public List<Categoria> listarTodos() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			String sql = "select * from categoria order by nome";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(resultSet.getInt("categoria_id"));
				categoria.setNome(resultSet.getString("nome"));
				categorias.add(categoria);
			}
			stmt.close();
			return categorias;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Categoria>();
		}
	}
	
	public void alterar(Categoria categoria) throws Exception {
		try {
			String sqlUpdate = "update categoria set nome = ? "
					+ "where categoria_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getId());
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
