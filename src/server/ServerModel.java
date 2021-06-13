package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ServerModel {

	private  ArrayList<User> userList = new ArrayList<>();
	private ArrayList<ToDo> toDoList = new ArrayList<>();

	private static String USERS = "Users.txt";
	private static String TODO = "TODO.txt";
	private static String SEPARATOR = "|";

	public ServerModel() {
		
	}

	public  ArrayList<User> getUserList() {
		return userList;
	}

	public  ArrayList<ToDo> getToDoList() {
		return toDoList;
	}

}
