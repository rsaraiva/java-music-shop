package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarPesquisar {

	public static void main(String[] args) {
		try {
			String stringBusca = "corda";
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "select * from categoria " +
					"where upper(nome) like ? order by nome";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%"+stringBusca.toUpperCase()+"%");
			ResultSet resultSet = stmt.executeQuery();
			boolean encontrou = false;
			while(resultSet.next()) {
				encontrou = true;
				System.out.print(resultSet.getInt("categoria_id") + " - ");
				System.out.println(resultSet.getString("nome"));
			}
			if (!encontrou) System.out.println("Nenhum registro encontrado.");
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
