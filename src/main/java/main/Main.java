package main;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;
import gui.MainGUI;

public class Main {

	public static void main(String[] args) {
		SqlCommandsImpl sqlCommands = new SqlCommandsImpl();

//		sqlCommands.createTableTODO();
		
		
		//TodoImpl t = new TodoImpl("Müll rausbringen", "Plastickmüll rausbringen", 5);
//		sqlCommands.newInsertSqlDatenbank(t);
		sqlCommands.editSpalteDatenbank(3, sqlCommands.selectNote(3));
		
		MainGUI gui = new MainGUI();
		gui.openWindow(sqlCommands.selectNote(3));
	}

}
