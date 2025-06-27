import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlCommandsImpl implements SqlCommands {
	/**
	 * Erstellt Tabelle mit Spalten: id(int), note(Text) und editedDate(DateTime)
	 */
	public void createTableTODO() {
		String sql = "CREATE TABLE IF NOT EXISTS todo ("
					+"id INTEGER PRIMARY KEY AUTOINCREMENT," + "priority INTEGER,"
					+"note TEXT NOT NULL,"
					+"editedDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)"
					+ "idFinished BOOLEAN NOT NULL CHECK (mycolumn IN (0, 1));";

		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Fügt der Tabelle todo Values hinzu (todo und priority)
	 */
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
	/**
	 * Bearbeitet vorhandene Spalten (newNote(String), priority(int)) über Primary Key(id(int))
	 */
	public void editSpalteDatenbank(int id, int priority, String newNote) {
		String sql = "UPDATE todo SET note = ?, priority = ? WHERE id = ?;";

		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, newNote);
			pstmt.setInt(2, priority);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Setzt isFinished(Boolean) auf True über Primary Key(id(int))
	 */
	public void todoIsFinished(int id) {
		String sql = "UPDATE todo set isFinished = ? WHERE id = ?;";
		
		try(Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Löscht Spalten über den Primary Key(id(int))
	 */
	public void deleteSpalteDatenbank(int id) {
		String sql = "DELETE FROM todo WHERE id = ?;";
		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Gibt alles aus der Datenbank aus
	 */
	public void selectAllDatenbank() {
		String sql = "SELECT * FROM todo;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("priority = " + rs.getInt("priority"));

				System.out.println("name = " + rs.getString("note"));

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
