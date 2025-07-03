package datenbank;

public class TodoImpl {

	private String name;
	private String note;
	private int priority;
	private boolean isDone;

	public TodoImpl(String name, String note, int priority) {
		this.name = name;
		this.note = note;
		this.priority = priority;
	}

	public TodoImpl(String name, String note, int priority, boolean isDone) {
		this.name = name;
		this.note = note;
		this.priority = priority;
		this.isDone = isDone;
	}

	public TodoImpl() {

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

	public boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(boolean isDone) {

		this.isDone = isDone;
	}

}
