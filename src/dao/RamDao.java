package dao;

import entity.User;

import java.util.ArrayList;

public class RamDao implements IDaoUser {
	private ArrayList<User> users=new ArrayList<User>();

	@Override
	public User add(String login, String password) {
		for (User u:users){
			if()
		}
		User user=new User()
		return null;
	}

	@Override
	public User add(String login) {
		return null;
	}

	@Override
	public User getUser(String login) {
		return null;
	}

	@Override
	public boolean remove(String login) {
		return false;
	}

	@Override
	public ArrayList<User> getAllUser() {
		return this.users;
	}
}
