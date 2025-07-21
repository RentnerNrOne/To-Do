package export;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import datenbank.TodoImpl;

public class ExportInJSON {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public void exportInJSON(TodoImpl todo) {

		if (todo == null || todo.getName() == null) {
			System.err.println("Fehler: Todo oder Name ist null.");
			return;
		}

		String fileName = todo.getName().replaceAll("[\\\\/:*?\"<>|]", "_") + ".json";

		try (FileWriter writer = new FileWriter(fileName)) {
			gson.toJson(todo, writer);
			System.out.println("JSON-Datei wurde erfolgreich erstellt.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
