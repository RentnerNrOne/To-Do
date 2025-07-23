package main;

import datenbank.SqlCommandsImpl;
import datenbank.TodoImpl;
import gui.MainGUI;

public class Main {

	public static void main(String[] args) {
		SqlCommandsImpl sqlCommands = new SqlCommandsImpl();
		
		sqlCommands.createTableTODO();
		
//		for(int x = 0; x == sqlCommands.sortByPriority().size() - 1; x++) {
//			sqlCommands.sortByPriority().get(x);	
//		}
		
		//TodoImpl t = new TodoImpl("Müll rausbringen", "Plastickmüll rausbringen", 3);
		//sqlCommands.newInsertSqlDatenbank(t);
		//sqlCommands.editSpalteDatenbank(7, sqlCommands.selectNote(7));
		
		MainGUI gui = new MainGUI();
		gui.openWindow();
	}

}
