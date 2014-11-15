package dao;


import entity.User;

import java.util.ArrayList;

public interface IDaoUser {
	public User add(String login,String password);
	public User add(String login);
	public User getUser(String login);
	public boolean remove(String login);
	public ArrayList<User> getAllUser();
}
