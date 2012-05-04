package br.com.people.musicshop.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.people.musicshop.factory.ConnectionFactory;

public class TestarExclusao {

	public static void main(String[] args) {
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"delete from categoria where categoria_id = ?");
			prepareStatement.setInt(1, 4);
			int linhasAfetadas = prepareStatement.executeUpdate();
			if (linhasAfetadas > 0) {
				System.out.println("Excluído com sucesso!");
			} else {
				System.out.println("Não encontrado para excluir.");
			}
			prepareStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

