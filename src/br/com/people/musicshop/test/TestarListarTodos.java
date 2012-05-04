package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarListarTodos {

	public static void main(String[] args) {
		try {
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "select * from categoria order by nome";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				System.out.print(resultSet.getInt("categoria_id") + " - ");
				System.out.println(resultSet.getString("nome"));
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
