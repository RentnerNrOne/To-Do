package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import datenbank.TodoImpl;


public class MainGUI {

	public void openWindow(TodoImpl t) {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(850, 1080);
		
		mainFrame.setLayout(null);

		mainFrame.add(addButton(mainFrame));

		//mainFrame.add(todoTextField());
		mainFrame.add(todoNameTextField(t));
		
		mainFrame.setVisible(true);
	}

	public JButton addButton(JFrame mainFrame) {
		JButton addButton = new JButton("+");
		//noch ändern
		addButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Test"));
		addButton.setFont(new Font("Arial", Font.BOLD, 25));
		addButton.setBounds(670, 900, 80, 80);
		addButton.setSize(60, 60);
		return addButton;
	}
	
	public JTextField todoTextField() {
		JTextField todoTextField = new JTextField("Test");
		todoTextField.setBounds(100, 100, 80, 80);
		todoTextField.setSize(625, 150);
		return todoTextField;
	}

	public JPanel todoNameTextField(TodoImpl todo) {
		EditTodoGUI secondFrame = new EditTodoGUI();
		
		JPanel panel = new JPanel(new BorderLayout());
		JButton editTodo = new JButton("x");
		JLabel namenTodo = new JLabel(todo.getName());
		
		editTodo.setFont(new Font("Arial", Font.BOLD, 25));
		namenTodo.setFont(new Font("Arial", Font.BOLD, 25));
		
		editTodo.addActionListener(e -> secondFrame.secondFrame());
		//editTodo.addActionListener(e -> (secondFrame.secondFrame()) && );
		
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(100, 100, 80, 80);
		panel.setSize(625, 60);
		
		panel.add(editTodo, BorderLayout.EAST);
		panel.add(namenTodo, BorderLayout.WEST);
		
		return panel;
	}
}
