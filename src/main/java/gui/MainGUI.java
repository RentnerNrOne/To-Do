package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

		mainFrame.add(addButton());
		mainFrame.add(menu());
		test(sqlCommands.sortByPriorityDESC());

//		for (int x = 0; x <= sqlCommands.sortByPriorityDESC().size() - 1; x++) {
//
//			mainFrame.add(todoNameTextField(sqlCommands.sortByPriorityDESC().get(x), posY));
//			posY +=80;
//		}

		mainFrame.setVisible(true);
	}
	//wichtig
	public List<TodoImpl> test(List<TodoImpl> t){
		todos = t;
		for (int x = 0; x <= todos.size() - 1; x++) {
			mainFrame.add(todoNameTextField(todos.get(x), posY));
			posY +=80;
		}
		return todos;
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

		item1.addActionListener(e -> {test(sqlCommands.sortByPriorityDESC()); refreshTodos(todos);});
		item2.addActionListener(e -> {test(sqlCommands.sortByPriorityASC()); refreshTodos(todos);});
		
		item3.addActionListener(e -> {test(sqlCommands.sortWherePriority(1)); refreshTodos(todos);});
		item4.addActionListener(e -> {test(sqlCommands.sortWherePriority(2)); refreshTodos(todos);});
		item5.addActionListener(e -> {test(sqlCommands.sortWherePriority(3)); refreshTodos(todos);});
		
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
		JLabel namenTodo = new JLabel(todo.getName());

		editTodo.setFont(new Font("Arial", Font.BOLD, 25));
		namenTodo.setFont(new Font("Arial", Font.BOLD, 25));

		editTodo.addActionListener(e -> editFrame.editTodoFrame());
		// editTodo.addActionListener(e -> (editFrame.editFrame(todo)) && );

		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(posX, posY, 625, 60);

		panel.add(editTodo, BorderLayout.EAST);
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
