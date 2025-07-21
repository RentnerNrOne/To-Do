package gui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;
import export.ExportInJSON;

public class EditTodoGUI {
	private	SqlCommandsImpl sqlCommands;
	private JTextField textFieldName;
	private JTextArea textFieldTodoNote;
	private JCheckBox isDoneCheckbox;
	private JSlider prioritySlider;
	private TodoImpl todo;
	private ExportInJSON exportJSON;

	private int min;
	private int max;

	public EditTodoGUI(TodoImpl todo) {
		min = 1;
		max = 3;
		this.todo = todo;
		sqlCommands = new SqlCommandsImpl();
		exportJSON = new ExportInJSON();
	}

	public JFrame editTodoFrame() {
		JFrame secondFrame = new JFrame();
		secondFrame.setSize(850, 1080);
		secondFrame.setLayout(null);

		secondFrame.add(isDoneCheckbox());
		secondFrame.add(todoNameTextField(todo));
		secondFrame.add(todoNoteTextField(todo));
		secondFrame.add(prioritySlider(todo));
		secondFrame.add(saveButton(secondFrame));
		secondFrame.add(exportButton());
		secondFrame.add(progressBar());

		secondFrame.setVisible(true);
		return secondFrame;
	}

	public JCheckBox isDoneCheckbox() {
		isDoneCheckbox = new JCheckBox("Erledigt");

		isDoneCheckbox.setSelected(todo.getIsDone());

		isDoneCheckbox.setBounds(600, 100, 600, 100);
		return isDoneCheckbox;
	}

	// Objekt muss noch hinzugefügt werden
	public JTextField todoNameTextField(TodoImpl todo) {
		textFieldName = new JTextField(todo.getName());

		textFieldName.setBounds(110, 110, 450, 80);

		return textFieldName;
	}

	// Objekt muss noch hinzugefügt werden
	public JTextArea todoNoteTextField(TodoImpl todo) {
		textFieldTodoNote = new JTextArea(todo.getNote());

		textFieldTodoNote.setBounds(110, 280, 600, 120);

		return textFieldTodoNote;
	}

	public JSlider prioritySlider(TodoImpl todo) {
		prioritySlider = new JSlider(JSlider.HORIZONTAL, min, max, todo.getPriority());
		prioritySlider.setMajorTickSpacing(1);

		prioritySlider.setBounds(120, 380, 600, 120);

		prioritySlider.setPaintTicks(true);
		prioritySlider.setPaintLabels(true);
		prioritySlider.setSnapToTicks(true);
		return prioritySlider;
	}
	
	public JButton exportButton() {
		JButton exportButton = new JButton("<html><b><font size='20'>Export</font></b></html>");
		
		exportButton.setBounds(250, 500, 350, 100);
		exportButton.addActionListener(e -> {
		
			exportJSON.exportInJSON(todo);
			
		});
		
		return exportButton;
	}
	public JProgressBar progressBar() {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(225, 650, 400, 50);
		
		
		
		return progressBar;
	}

	public JButton saveButton(JFrame secondFrame) {
		JButton saveButton = new JButton("<html><b><font size='25'>Save</font></b></html>");

		saveButton.addActionListener(e -> {
			todo.setName(textFieldName.getText());
			todo.setNote(textFieldTodoNote.getText());
			todo.setPriority(prioritySlider.getValue());
			todo.setIsDone(isDoneCheckbox.isSelected());
			sqlCommands.editSpalteDatenbank(todo.getId(), todo);
			secondFrame.dispose();

			System.out.println("TODO bearbeitet");
		});
		saveButton.setBounds(110, 800, 600, 100);
		return saveButton;
	}

}
