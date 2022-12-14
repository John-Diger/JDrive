package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBCRepository {
	private final ConnectionManager connectionManager;
	private final Logger logger;

	public JDBCRepository(ConnectionManager connectionManager, Logger logger) {
		this.connectionManager = connectionManager;
		this.logger = logger;
	}

	public void insertFile(InputStream inputStream) {
		final String query = "INSERT INTO bucket(shared_file, created_at, modified_at, status) VALUES(?,?,?,?)";
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setBlob(1, inputStream);
			preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
			preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
			preparedStatement.setBoolean(4, Boolean.TRUE);

			int row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdrive", "root", "password");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
