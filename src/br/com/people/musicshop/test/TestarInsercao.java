package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarInsercao {

	public static void main(String[] args) {
		try {
			Connection connection = new ConnectionFactory().getConnection();
			
			// cara novo: Statement
			//Statement statement = connection.createStatement();
			//statement.execute("insert into categoria(nome) values('Video Aulas')");
			
			// prepared statement, melhor!
			PreparedStatement prepareStatement = 
					connection.prepareStatement(
							"insert into categoria(nome) values (?)");
			prepareStatement.setString(1, "Cordas de Aço");
			prepareStatement.executeUpdate();
			prepareStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
