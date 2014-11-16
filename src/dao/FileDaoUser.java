package dao;

import entity.User;

import java.io.*;
import java.util.ArrayList;

public class FileDaoUser implements IDaoUser {
	private enum Cools {LOGIN, PASSWORD, NAME, PHONE, ONLINE}

	private File uFile;
	String path = FileDaoUser.class.getResource("Users").getPath();
	FileDaoUser(){
		uFile=new File(path);
	}

	@Override
	public boolean add(User u) throws IllegalArgumentException{
		if(u==null){
			throw new IllegalArgumentException("User can not be null!");
		}
		boolean result=false;
		try {
			FileWriter fileWriter = new FileWriter(uFile, true);
			String user = userToString(u) + "\n";
			fileWriter.write(user);
			fileWriter.flush();
			fileWriter.close();
			result=true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User getUser(String login) {
		User u = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(uFile)));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String user[] = line.split(";");
				if (login.equals(user[Cools.LOGIN.ordinal()])) {
					u = new User(user[Cools.LOGIN.ordinal()], user[Cools.PASSWORD.ordinal()]);
					u.setName(user[Cools.NAME.ordinal()]);
					u.setPhone(user[Cools.PHONE.ordinal()]);
					u.setOnline(user[Cools.ONLINE.ordinal()] == "1" ? true : false);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean update(User u) {
		remove(u.getLogin());
		return add(u);
	}

	@Override
	public boolean remove(String login) {
		ArrayList<User> userArrayList=getAllUser();
		User user=null;
		boolean result=false;
		for (User u:userArrayList){
			if(u.getLogin().equals(login)){
				user=u;
				break;
			}
		}
		if(user instanceof User) {
			result=userArrayList.remove(user);
			try {
				FileOutputStream fileOutputStream=new FileOutputStream(uFile);
				String userString;
				for (User u:userArrayList){
					fileOutputStream.write((userToString(u) + "\n").getBytes());
				}
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public ArrayList<User> getAllUser() {
		User u;
		ArrayList<User> userArrayList = new ArrayList<User>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(uFile)));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String user[] = line.split(";");
				u = new User(user[Cools.LOGIN.ordinal()], user[Cools.PASSWORD.ordinal()]);
				u.setName(user[Cools.NAME.ordinal()]);
				u.setPhone(user[Cools.PHONE.ordinal()]);
				u.setOnline(user[Cools.ONLINE.ordinal()] == "1" ? true : false);
				userArrayList.add(u);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userArrayList;
	}
	private String userToString(User u){
		String []str=new String[Cools.values().length];
		str[Cools.LOGIN.ordinal()]=u.getLogin();
		str[Cools.PASSWORD.ordinal()]=u.getPassword();
		str[Cools.NAME.ordinal()]=u.getName();
		str[Cools.PHONE.ordinal()]=u.getPhone();
		str[Cools.ONLINE.ordinal()]=u.getOline()?"1":"0";
		String user="";
		for (String s : str) {
			user+=s+";";
		}
		return user;
	}
}
