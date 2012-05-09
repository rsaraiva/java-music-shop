package br.com.people.musicshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.people.musicshop.entity.Produto;

public class ProdutoDAO {
	
	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public Integer inserir(Produto produto) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into produto(categoria_id, nome, valor) " +
							"values (?,?,?)");
			prepareStatement.setInt(1, produto.getCategoria().getId());
			prepareStatement.setString(2, produto.getNome());
			prepareStatement.setFloat(3, produto.getValor());
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
	
	public Produto carregar(Integer id) {
		CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
		try {
			String sql = "select * from produto where produto_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			Produto produto = null;
			if (resultSet.next()) {
				produto = new Produto();
				produto.setId(resultSet.getInt("produto_id"));
				produto.setCategoria(categoriaDAO.carregar(resultSet.getInt("categoria_id")));
				produto.setNome(resultSet.getString("nome"));
				produto.setValor(resultSet.getFloat("valor"));
			}
			stmt.close();
			
			return produto;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void excluir(Integer id) throws Exception {
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(
					"delete from produto where produto_id = ?");
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
	
	public List<Produto> listarTodos() {
		CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "select * from produto order by nome";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				Produto produto = new Produto();
				produto.setId(resultSet.getInt("produto_id"));
				produto.setCategoria(categoriaDAO.carregar(resultSet.getInt("categoria_id")));
				produto.setNome(resultSet.getString("nome"));
				produto.setValor(resultSet.getFloat("valor"));
				produtos.add(produto);
			}
			stmt.close();
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Produto>();
		}
	}
	
	public void alterar(Produto produto) throws Exception {
		try {
			String sqlUpdate = "update produto set nome = ?, categoria_id = ?, valor = ? "
					+ "where produto_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getCategoria().getId());
			stmt.setFloat(3, produto.getValor());
			stmt.setInt(4, produto.getId());
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
