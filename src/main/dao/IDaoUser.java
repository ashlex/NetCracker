package main.dao;


import main.entity.User;

import java.util.ArrayList;

public interface IDaoUser {
	public boolean add(User u) throws IllegalArgumentException;
	public User getUser(String login);
	public boolean update(User u);
	public boolean remove(String login);
	public ArrayList<User> getAllUser();
}
