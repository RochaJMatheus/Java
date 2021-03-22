package ConnectionDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public Connection getConnection() {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		}
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loja?useSSL=false", "root", "1234");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return conn;

	}

}
