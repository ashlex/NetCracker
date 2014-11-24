package main.dao;

import main.entity.User;

import java.io.*;
import java.util.ArrayList;

public class FileDaoUser implements IDaoUser {
	@Override
	public boolean add(User u) throws IllegalArgumentException {
		return false;
	}

	@Override
	public User getUser(String login) {
		return null;
	}

	@Override
	public boolean update(User u) {
		return false;
	}

	@Override
	public boolean remove(String login) {
		return false;
	}

	@Override
	public ArrayList<User> getAllUser() {
		return null;
	}

	private enum Cools {LOGIN, PASSWORD, NAME, PHONE, ONLINE,ROLE}

	private File uFile;
//	private String path = FileDaoUser.class.getResource("Users").getPath();
	FileDaoUser(File userDataFile){
		if(userDataFile!=null) {
			uFile = userDataFile;
		}
	}


}
