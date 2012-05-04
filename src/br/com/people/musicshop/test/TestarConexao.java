package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestarConexao {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//try {
			// registra o driver (classe) na memoria
			Class.forName("com.mysql.jdbc.Driver");
			
			// string de conexao jdbc
			String url = "jdbc:mysql://localhost:3306/musicshop";
			
			// abre a conexao
			Connection connection = DriverManager.getConnection(url, "root", "people");
			
			// fechar
			connection.close();
			
		
		//} catch (SQLException e) {
		//	e.printStackTrace();
			
		//} catch (ClassNotFoundException e) {
		//	e.printStackTrace();
		//}
	}
}
