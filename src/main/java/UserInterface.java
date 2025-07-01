
public interface UserInterface {
	/**
	 * schreibt alle Todos sotiert nach Priorität aus
	 */
	public void ausschreibenTodos();
	
/**
 * wählt zwischen methoden per "zahl" und gibt "id" als Parameter weiter
 * @param zahl
 * @param id
 */
	public void todosBearbeiten(int zahl, int id);
	
	/**
	 * 
	 * @param id
	 */
	public void todoBearbeiten(int id);
		public void editAll(int id, int priority, String newNote);
		public void todoNewNote(int id, String newNote);
		public void todoNewPriority(int id, int Priority);
	public void todoErledigt(int id);
	public void todoLoeschen(int id);
	
	
}
