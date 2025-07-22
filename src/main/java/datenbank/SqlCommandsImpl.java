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

	public List<TodoImpl> sortByPriorityASC() {
		List<TodoImpl> todoList = new ArrayList<TodoImpl>();
		String sql = "SELECT id, name, note, priority, isDone, editedDate FROM todo ORDER BY priority ASC, isDone ASC;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				TodoImpl todo = new TodoImpl();
				todo.setName(rs.getString("name"));
				todo.setNote(rs.getString("note"));
				todo.setPriority(rs.getInt("priority"));
				todo.setIsDone(rs.getBoolean("isDone"));
				todo.setId(rs.getInt("id"));
				todo.setTime(rs.getString("editedDate"));

				todoList.add(todo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return todoList;

	}

	public List<TodoImpl> sortByPriorityDESC() {
		List<TodoImpl> todoList = new ArrayList<TodoImpl>();
		String sql = "SELECT id, name, note, priority, isDone, editedDate FROM todo ORDER BY priority DESC, isDone DESC;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				TodoImpl todo = new TodoImpl();
				todo.setName(rs.getString("name"));
				todo.setNote(rs.getString("note"));
				todo.setPriority(rs.getInt("priority"));
				todo.setIsDone(rs.getBoolean("isDone"));
				todo.setId(rs.getInt("id"));
				todo.setTime(rs.getString("editedDate"));
				
				todoList.add(todo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return todoList;

	}
	public List<TodoImpl> sortWherePriority(int priority) {
		List<TodoImpl> todoList = new ArrayList<TodoImpl>();
		String sql = "SELECT id, name, note, priority, isDone, editedDate FROM todo WHERE priority = ? ORDER BY isDone DESC;";

		try (Connection conn = SqlDatenbankConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, priority);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TodoImpl todo = new TodoImpl();
				todo.setName(rs.getString("name"));
				todo.setNote(rs.getString("note"));
				todo.setPriority(rs.getInt("priority"));
				todo.setIsDone(rs.getBoolean("isDone"));
				todo.setId(rs.getInt("id"));
				todo.setTime(rs.getString("editedDate"));
				
				todoList.add(todo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return todoList;

	}
	public TodoImpl selectNote(int id) {
		TodoImpl todo = new TodoImpl();

		String sql = "SELECT name, note, priority, isDone, editedDate FROM todo WHERE id = ?;";
		try (Connection conn = SqlDatenbankConnection.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String note = rs.getString("note");
				int priority = rs.getInt("priority");
				boolean isDone = rs.getBoolean("isDone");
				String time = rs.getString("editedDate");
				todo.setName(name);
				todo.setNote(note);
				todo.setIsDone(isDone);
				todo.setPriority(priority);
				todo.setTime(time);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return todo;
	}


}
