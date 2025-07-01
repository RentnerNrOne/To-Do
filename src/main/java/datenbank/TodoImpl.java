package datenbank;

public class TodoImpl {
	

	private String name;
	private String note;
	private int priority;

	public TodoImpl(String name, String note, int priority) {
		this.name = name;
		this.note = note;
		this.priority = priority;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
