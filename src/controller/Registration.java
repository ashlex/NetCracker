package controller;

import dao.DaoFactory;
import dao.IDaoUser;
import entity.User;

public class Registration {
	public static User newUser(String login,String password) throws IllegalArgumentException{
		if(login==null||password==null){
			throw new IllegalArgumentException();
		}
		IDaoUser iDaoUser= DaoFactory.getInstance().getFileDaoUser();
		User u=new User(login, password);
		iDaoUser.add(u);
		return u;
	}
	public static User newUser(String login){
		String pass=(int)(Math.random()*1000000)+"";
		return newUser(login,pass);
	}
}
