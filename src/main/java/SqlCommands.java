import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlCommands {

	public void createTableTODO() {
		String sql = "CREATE TABLE IF NOT EXISTS todo ("
			    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
			    + "priority INTEGER,"
			    + "note TEXT NOT NULL,"
			    + "edited_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP);";
		
		try(Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertSqlDatenbank(String todo, int priority) {
		String sql = "INSERT INTO todo (note, priority) VALUES (?, ?);";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, todo);
			pstmt.setInt(2, priority);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void updateSqlDatenbank(int id, int priority, String newNote) {
		String sql = "UPDATE todo"
				+ "SET note = '?', priority = '?'"
				+ "WHERE id = '?';";
		
		try(Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, newNote);
			pstmt.setInt(2, priority);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void selectAllDatenbank() {
		String sql = "SELECT * FORM todo;";
	}

}
