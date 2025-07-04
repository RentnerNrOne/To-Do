package datenbank;

import java.util.List;

public interface SqlCommands {
	
	/**
	 * Erstellt Tabelle mit Spalten: id(int), note(Text) und editedDate(DateTime)
	 */
	public void createTableTODO();
	
	/**
	 * Fügt der Tabelle todo Values hinzu (todo und priority) 
	 */
	public void newInsertSqlDatenbank(TodoImpl t);
	/**
	 * Bearbeitet vorhandene Spalten (newNote(String), priority(int)) über Primary Key(id(int))
	 */
	
	public void editSpalteDatenbank(int id, TodoImpl t);
	/**
	 * Setzt isFinished(Boolean) auf True über Primary Key(id(int))
	 */
	public void todoIsFinished(int id);
	/**
	 * Löscht Spalten über den Primary Key(id(int))
	 */
	public void deleteSpalteDatenbank(int id);
	
	/**
	 * Sotiert alle Notizen nach Priority: gibt Namen der Notiz(name(TEXT)), Notiz(note(TEXT)) und Priority aus
	 */
	public List<TodoImpl> sortByPriorityASC();
	public List<TodoImpl> sortByPriorityDESC();
	
	public List<TodoImpl> sortWherePriority(int priority);

	/**
	 * Gibt Namen der Notiz(name(TEXT)) und Notiz(note(TEXT)) selber aus
	 */
	public TodoImpl selectNote(int id);
	
}
