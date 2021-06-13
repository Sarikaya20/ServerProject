package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import server.ToDo;
import server.User;

public class ServerModel {

	protected static ArrayList<User> userList = new ArrayList<>();
	protected static ArrayList<ToDo> toDoList = new ArrayList<>();

	private static String USERS = "Users.sav";
	private static String TODO = "ToDo.sav";
	private static String SEPARATOR = "|";

	public ServerModel() {
		
	}

	public static ArrayList<User> getUserList() {
		return userList;
	}

	public static ArrayList<ToDo> getToDoList() {
		return toDoList;
	}

	/**
	 * Save and restore server data
	 * ------------------------------------------------------------------------------------------------------------------
	 */
	/*public void writeSaveFileUsers() {
		File file = new File(USERS);
		try (FileWriter fileOut = new FileWriter(file)) {
			for (User user : userList) {
				String line = writeUser(user);
				fileOut.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Token is not saved - every user is logged out if the server crashes
	public String writeUser(User user) {
		String line =  user.getUsername() + SEPARATOR + user.getPassword() + "\n";
		return line;
	}

	public void writeSaveFileToDo() {
		File file = new File(TODO);
		try (FileWriter fileOut = new FileWriter(file)) {
			for (ToDo todo : toDoList) {
				String line = writeToDo(todo);
				fileOut.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("saved");
	}

	public String writeToDo(ToDo todo) {
		String line = todo.getID() + SEPARATOR + todo.getTitle() + SEPARATOR + todo.getPrio() + SEPARATOR
				+ todo.getDescription() + SEPARATOR + todo.getUser() + "\n";
		return line;
	}

	public void readSaveFileUser() {
		File file = new File(USERS);
		String data = "";
		try (BufferedReader fileIn = new BufferedReader(new FileReader(file))) {
			String line = fileIn.readLine();
			while (line != null) {
				User user = readUser(line);
				userList.add(user);
				line = fileIn.readLine();
			}
		} catch (FileNotFoundException e) {
			data = "Save file does not exist";
		} catch (IOException e) {
			data = e.getClass().toString();
		}
	}

	public User readUser(String line) {
		String[] attributes = line.split(SEPARATOR);
		int userID = -999;
		String userName = "-";
		String userPassword = "-";
		try {
			userID = Integer.valueOf(attributes[0]);
			userName = attributes[1];
			userPassword = attributes[2];
		} catch (Exception e) {
			userName = "-Error in Line-";
		}
		User user = new User(userName, userPassword, null);
		user.setID(userID); // restore ID
		return user;
	}

	public void readSaveFileToDo() {
		File file = new File(TODO);
		String data = "";
		try (BufferedReader fileIn = new BufferedReader(new FileReader(file))) {
			String line = fileIn.readLine();
			while (line != null) {
				ToDo todo = readToDo(line);
				toDoList.add(todo);
				line = fileIn.readLine();
			}
		} catch (FileNotFoundException e) {
			data = "Save file does not exist";
		} catch (IOException e) {
			data = e.getClass().toString();
		}
	}

	public ToDo readToDo(String line) {
		String[] attributes = line.split(SEPARATOR);
		int todoID = -999;
		String todoTitle = "-";
		String todoPriority = "-";
		String todoDescription = "-";
		String todoUser = "-";
		try {
			todoID = Integer.valueOf(attributes[0]);
			todoTitle = attributes[1];
			todoPriority = attributes[2];
			todoDescription = attributes[3];
			todoUser = attributes[4];
		} catch (Exception e) {
			todoTitle = "-Error in Line-";
		}
		ToDo todo = new ToDo(todoTitle, Prio.valueOf(todoPriority), todoDescription, todoUser);
		todo.setID(todoID);
		toDoList.add(todo);
		return todo;
	}*/
}

