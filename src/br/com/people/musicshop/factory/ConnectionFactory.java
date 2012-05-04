package br.com.people.musicshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/musicshop";
	
	{   // <-- bloco de inicializacao
		try {
			// registra o driver (classe) na memoria
			Class.forName("com.mysql.jdbc.Driver");

			// abre a conexao
			connection = DriverManager.getConnection(url, "root", "people");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
