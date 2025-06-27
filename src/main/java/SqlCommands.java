
public interface SqlCommands {
	
	
	public void createTableTODO();
	
	public void newInsertSqlDatenbank(String note, int priority, String name);

	public void editSpalteDatenbank(int id, int priority, String newNote);
	public void todoIsFinished(int id);
	public void deleteSpalteDatenbank(int id);

	public void selectAllDatenbank();
	
	public void sortByPriority();
	
	public void selectNote(int id);
	
}
