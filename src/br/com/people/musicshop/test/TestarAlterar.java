package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.people.musicshop.entity.Categoria;
import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarAlterar {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			Integer id = 3;
			Categoria categoria = new Categoria();
			
			connection = new ConnectionFactory().getConnection();
			String sql = "select * from categoria where categoria_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				categoria.setId(resultSet.getInt("categoria_id"));
				categoria.setNome(resultSet.getString("nome"));
			} else {
				throw new Exception("Não encontrado!");
			}
			
			// alterar:
			categoria.setNome("Acessórios de Guitarra");
			
			String sqlUpdate = "update categoria set nome = ? " +
					"where categoria_id = ?";
			stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getId());
			if (stmt.executeUpdate() > 0) {
				System.out.println("Sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			connection.close();
		}
	}
}
