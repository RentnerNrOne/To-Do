
public interface SqlCommands {
	
	
	public void createTableTODO();
	
	public void insertSqlDatenbank(String todo, int priority);

	public void editSpalteDatenbank(int id, int priority, String newNote);
	public void todoIsFinished(int id);
	public void deleteSpalteDatenbank(int id);

	public void selectAllDatenbank();
	
}
