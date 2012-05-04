package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarCarregarPorId {

	public static void main(String[] args) {
		try {
			Integer id = 67;
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "select * from categoria where categoria_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				System.out.println("Encontrado");
				System.out.println("id: " + resultSet.getInt("categoria_id"));
				System.out.println("nome: " + resultSet.getString("nome"));
			} else {
				System.out.println("Não encontrado!");
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
