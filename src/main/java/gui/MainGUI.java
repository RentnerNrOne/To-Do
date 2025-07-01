package gui;

import java.awt.Checkbox;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainGUI {

	public void openWindow() {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(850, 1080);
		mainFrame.setVisible(true);
		mainFrame.setLayout(null);
		
		mainFrame.add(isDoneCheckbox());
		mainFrame.add(addButton());
		mainFrame.add(todoTextField());
	}

	public JButton addButton() {
		JButton addButton = new JButton("+");
		addButton.setBounds(600, 900, 80, 80);
		return addButton;
	}

	public Checkbox isDoneCheckbox() {
		Checkbox isDoneCheckbox = new Checkbox("Erledigt");
		isDoneCheckbox.setBounds(612, 800, 80, 80);
		return isDoneCheckbox;
	}
	public JTextField todoTextField() {
		JTextField todoTextField = new JTextField("kjlasdf");
		todoTextField.setBounds(100, 100, 80, 80);
		todoTextField.setSize(625, 150);
		return todoTextField;
	}
}
