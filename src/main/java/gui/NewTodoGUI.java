package gui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;

public class NewTodoGUI {
	SqlCommandsImpl sqlCommands;
	JTextField textFieldName;
	JTextArea textFieldTodoNote;
	JCheckBox isDoneCheckbox;
	JSlider prioritySlider;
	TodoImpl todo;

	int min;
	int max;

	public NewTodoGUI() {
		min = 1;
		max = 3;
		todo = new TodoImpl();
		sqlCommands = new SqlCommandsImpl();

	}



	public JFrame newTodoFrame() {
		JFrame secondFrame = new JFrame();
		secondFrame.setSize(850, 1080);
		secondFrame.setLayout(null);

		secondFrame.add(isDoneCheckbox());
		secondFrame.add(textFieldName());
		secondFrame.add(textFieldTodoNote());
		secondFrame.add(prioritySlider());
		secondFrame.add(saveButton(secondFrame));

		secondFrame.setVisible(true);
		return secondFrame;
	}

	public JCheckBox isDoneCheckbox() {
		isDoneCheckbox = new JCheckBox("Erledigt");

		isDoneCheckbox.setSelected(todo.getIsDone());

		isDoneCheckbox.setBounds(600, 100, 600, 100);
		return isDoneCheckbox;
	}

	public JTextField textFieldName() {
		textFieldName = new JTextField();

		textFieldName.setBounds(110, 110, 450, 80);

		return textFieldName;
	}

	public JTextArea textFieldTodoNote() {
		textFieldTodoNote = new JTextArea();

		textFieldTodoNote.setBounds(110, 280, 600, 120);

		return textFieldTodoNote;
	}

	public JSlider prioritySlider() {
		prioritySlider = new JSlider(JSlider.HORIZONTAL, min, max, 2);
		prioritySlider.setMajorTickSpacing(1);

		prioritySlider.setBounds(120, 380, 600, 120);

		prioritySlider.setPaintTicks(true);
		prioritySlider.setPaintLabels(true);
		prioritySlider.setSnapToTicks(true);
		return prioritySlider;
	}

	public JButton saveButton(JFrame secondFrame) {

		JButton saveButton = new JButton("<html><b><font size='25'>Save</font></b></html>");

		saveButton.addActionListener(e -> {
			todo.setName(textFieldName.getText());
			todo.setNote(textFieldTodoNote.getText());
			todo.setPriority(prioritySlider.getValue());
			todo.setIsDone(isDoneCheckbox.isSelected());
			sqlCommands.newInsertSqlDatenbank(todo);
			secondFrame.dispose();

			System.out.println("TODO angelegt");
		});
		saveButton.setBounds(110, 800, 600, 100);
		return saveButton;
	}

}
