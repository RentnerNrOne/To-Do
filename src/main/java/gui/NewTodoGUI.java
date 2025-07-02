package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewTodoGUI {
	
	public JFrame newTodoFrame() {
		JFrame secondFrame = new JFrame();
		secondFrame.setSize(850, 1080);
		secondFrame.setLayout(null);
		
		secondFrame.add(isDoneCheckbox());
		secondFrame.add(todoNameTextField());
		secondFrame.add(todoNoteTextField());
		secondFrame.add(saveButton(secondFrame));
		
		String noteText = todoNoteTextField().getText();
		System.out.println(noteText);
		
		secondFrame.setVisible(true);
		return secondFrame;
	}
	public JCheckBox isDoneCheckbox() {
		JCheckBox isDoneCheckbox = new JCheckBox("Erledigt");
		//isDoneCheckbox.setPreferredSize(new Dimension(150, 30));
		//isDoneCheckbox.setFont(new Font("Arial", Font.PLAIN, 45));
		isDoneCheckbox.setBounds(600, 100, 80, 80);
		isDoneCheckbox.setSize(new Dimension(600, 100));
		return isDoneCheckbox;
	}	
	//Objekt muss noch hinzugefügt werden
	public JTextField todoNameTextField() {
		JTextField textFieldName = new JTextField();
		
		textFieldName.setBounds(110, 110, 80, 80);
		textFieldName.setSize(450, 80);
		
		return textFieldName;
	}
	//Objekt muss noch hinzugefügt werden
	public JTextArea todoNoteTextField() {
		JTextArea textFieldTodoNote = new JTextArea();
		
		textFieldTodoNote.setBounds(110, 280, 80, 80);
		textFieldTodoNote.setSize(600, 120);
		
		return textFieldTodoNote;
	}
	
	public JButton saveButton(JFrame secondFrame) {
		JButton saveButton = new JButton("<html><b><font size='25'>Save</font></b></html>");
		
		//saveButton.addActionListener(e -> () && (secondFrame.dispose()));
		saveButton.addActionListener(e -> secondFrame.dispose());
		saveButton.setBounds(110, 800, 80, 80);
		saveButton.setSize(600, 100);
		return saveButton;
	}

}
