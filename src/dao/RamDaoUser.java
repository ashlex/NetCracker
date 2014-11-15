package dao;

import entity.User;

import java.util.ArrayList;

public class RamDaoUser implements IDaoUser {
	private ArrayList<User> users = new ArrayList<User>();

	@Override
	public User add(String login, String password) {
		if (getUser(login) instanceof User) {
			return null;
		}
		User u = new User(login, password);
		users.add(u);
		return u;
	}

	@Override
	public User add(String login) {
		if (getUser(login) instanceof User) {
			return null;
		}
		String password=(int)(Math.random()*10000)+"";
		User u = new User(login, password);
		users.add(u);
		return u;
	}

	@Override
	public User getUser(String login) {
		for (User u : users) {
			if (u.getLogin().equals(login)) return u;
		}
		return null;
	}

	@Override
	public boolean remove(String login) {
		return users.remove(getUser(login));
	}

	@Override
	public ArrayList<User> getAllUser() {
		return this.users;
	}
}
