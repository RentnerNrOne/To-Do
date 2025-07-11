package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;

public class MainGUI {
	private List<TodoImpl> todos;
	private JFrame mainFrame;
	private SqlCommandsImpl sqlCommands;
	private int posX;
	private int posY;

	public MainGUI() {
		sqlCommands = new SqlCommandsImpl();
		posX = 80;
		posY = 100;
	}

	public void openWindow() {
		mainFrame = new JFrame();
		mainFrame.setSize(850, 1080);

		mainFrame.setLayout(null);

		mainFrame.add(menu());
		refreshTodos(sqlCommands.sortByPriorityDESC());

		mainFrame.add(addButton());
		mainFrame.setVisible(true);
	}


	public JMenuBar menu() {
		JMenuBar menu = new JMenuBar();
		menu.setBounds(0, 0, 850, 25);

		JMenu sortPrio = new JMenu("Filtern");
		JMenu sortStatus = new JMenu("Sotieren");

		JMenuItem item1 = new JMenuItem("Absteigend");
		JMenuItem item2 = new JMenuItem("Aufsteigend");

		JMenuItem item3 = new JMenuItem("Priorität 1");
		JMenuItem item4 = new JMenuItem("Priorität 2");
		JMenuItem item5 = new JMenuItem("Priorität 3");

		item1.addActionListener(e -> {
			refreshTodos(sqlCommands.sortByPriorityDESC());
		});
		item2.addActionListener(e -> {
			refreshTodos(sqlCommands.sortByPriorityASC());
		});

		item3.addActionListener(e -> {
			refreshTodos(sqlCommands.sortWherePriority(1));
		});
		item4.addActionListener(e -> {
			refreshTodos(sqlCommands.sortWherePriority(2));
		});
		item5.addActionListener(e -> {
			refreshTodos(sqlCommands.sortWherePriority(3));
		});

		sortStatus.add(item1);
		sortStatus.add(item2);

		sortPrio.add(item3);
		sortPrio.add(item4);
		sortPrio.add(item5);

		menu.add(sortPrio);
		menu.add(sortStatus);

		return menu;
	}

	public JButton addButton() {
		JButton addButton = new JButton("+");
		NewTodoGUI newTodoFrame = new NewTodoGUI();
		// noch ändern
		addButton.addActionListener(e -> newTodoFrame.newTodoFrame());
		addButton.setFont(new Font("Arial", Font.BOLD, 25));
		addButton.setBounds(670, 900, 60, 60);
		return addButton;
	}

	public JTextField todoTextField() {
		JTextField todoTextField = new JTextField("Test");
		todoTextField.setBounds(100, 100, 625, 150);
		return todoTextField;
	}

	public JPanel todoNameTextField(TodoImpl todo, int posY) {
		EditTodoGUI editFrame = new EditTodoGUI(todo);

		JPanel panel = new JPanel(new BorderLayout());
		JButton editTodo = new JButton("x");
		JButton deleteTodo = new JButton("X");
		JLabel namenTodo = new JLabel(todo.getName());

		editTodo.setFont(new Font("Arial", Font.BOLD, 25));
		deleteTodo.setFont(new Font("Arial", Font.BOLD, 25));
		namenTodo.setFont(new Font("Arial", Font.BOLD, 25));

		editTodo.addActionListener(e -> editFrame.editTodoFrame());
		// editTodo.addActionListener(e -> (editFrame.editFrame(todo)) && );
		deleteTodo.addActionListener(e -> sqlCommands.deleteSpalteDatenbank(todo.getId()));

		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(posX, posY, 625, 60);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonsPanel.add(editTodo);
		buttonsPanel.add(deleteTodo);

		panel.add(buttonsPanel);
		panel.add(namenTodo, BorderLayout.WEST);

		return panel;
	}

	private void refreshTodos(List<TodoImpl> todos) {
		// Remove old todo panels
		mainFrame.getContentPane().removeAll();
		mainFrame.add(addButton()); // re-add add button
		mainFrame.add(menu());

		posY = 100; // Reset vertical position
		for (TodoImpl todo : todos) {
			mainFrame.add(todoNameTextField(todo, posY));
			posY += 80;
		}

		mainFrame.revalidate();
		mainFrame.repaint();
	}
}
