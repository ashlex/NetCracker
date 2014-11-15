package controller;


import dao.DaoFactory;
import entity.User;

public class Authentication {

	public static boolean logIn(String login,String password) throws IllegalArgumentException{
		User u=DaoFactory.getInstance().getDaoUser("ramDaoUser").getUser(login);
		if(u==null){
			throw new IllegalArgumentException("This login not found.");
		}
		if(u.verification(password)) {
			if (!u.getOline()) {
				u.setOnline(true);
				return true;
			}
		}
		return false;
	}

	public static void logOut(String login){
		User u=DaoFactory.getInstance().getDaoUser("ramDaoUser").getUser(login);
		if(u==null){
			throw new IllegalArgumentException("This login not found.");
		}

	}

}
