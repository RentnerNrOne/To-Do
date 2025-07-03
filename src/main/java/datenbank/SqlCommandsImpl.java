package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlCommandsImpl implements SqlCommands {

	public void createTableTODO() {
		String sql = "CREATE TABLE IF NOT EXISTS todo (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "priority INTEGER,"
				+ "name TEXT NOT NULL," + "note TEXT NOT NULL,"
				+ "editedDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
				+ "isDone BOOLEAN NOT NULL CHECK (isDone IN (0, 1)));";

		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void newInsertSqlDatenbank(TodoImpl t) {
		String sql = "INSERT INTO todo (name, note, priority, isDone) VALUES (?, ?, ?, ?);";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getNote());
			pstmt.setInt(3, t.getPriority());
			pstmt.setBoolean(4, t.getIsDone());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void editSpalteDatenbank(int id, TodoImpl t) {
		String sql = "UPDATE todo SET name = ?, note = ?, priority = ?, isDone = ? WHERE id = ?;";

		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getNote());
			pstmt.setInt(3, t.getPriority());
			pstmt.setBoolean(4, t.getIsDone());
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void todoIsFinished(int id) {
		String sql = "UPDATE todo set isDone = ? WHERE id = ?;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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

	public void selectAllDatenbank() {
		String sql = "SELECT * FROM todo;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("priority = " + rs.getInt("priority"));

				System.out.println("name = " + rs.getString("name"));
				System.out.println("note = " + rs.getString("note"));

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void sortByPriority() {

		String sql = "SELECT name, note, priority FROM todo ORDER BY priority DESC;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(
						rs.getString("name") + ":\n" + rs.getString("note") + "\nPriority: " + rs.getInt("priority"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public TodoImpl selectNote(int id) {
		TodoImpl todo = new TodoImpl();
		
		String sql = "SELECT name, note, priority, isDone FROM todo WHERE id = ?;";
		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String note = rs.getString("note");
				int priority = rs.getInt("priority");
				boolean isDone = rs.getBoolean("isDone");
				todo.setName(name);
				todo.setNote(note);
				todo.setIsDone(isDone);
				todo.setPriority(priority);
				System.out.println(
						rs.getString("name") + ":\n" + rs.getString("note") + "\nPriority: " + rs.getInt("priority"));

			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return todo;
	}

}
