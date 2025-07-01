package datenbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDatenbankConnection {
	private static final String URL = "jdbc:sqlite:To-Do.db";
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(URL);
	}
}