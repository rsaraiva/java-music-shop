package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarConnectionFactory {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("conectado a " + connection.getCatalog());
		connection.close();
	}
}
