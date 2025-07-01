package main;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;
import gui.MainGUI;

public class Main {

	public static void main(String[] args) {
//		SqlCommandsImpl sqlCommands = new SqlCommandsImpl();
//		
//		sqlCommands.createTableTODO();
	
		TodoImpl t = new TodoImpl("Müll rausbringen", "Papiermüll rausbringen", 5);
//		sqlCommands.newInsertSqlDatenbank(t);
		
		MainGUI gui = new MainGUI();
		gui.openWindow(t);
	}

}
