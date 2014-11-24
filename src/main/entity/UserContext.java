package main.entity;

import main.IObservable;
import main.IObserver;

import java.util.HashSet;
import java.util.Set;

public class UserContext implements IObservable{
	private Set<IObserver> observers=new HashSet<IObserver>();
	private String nickname;
	private String password;
	private String name;
	private int role;
	private boolean online;


	public UserContext() {
		this.nickname = null;
		this.password = null;
		this.name = null;
		this.role = 0;
		this.online = false;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if (nickname!=null && !nickname.equals("")) {
			this.nickname = nickname;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null && !password.equals("")) {
			this.password = password;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!name.equals("")) {
			this.name = name;
		}
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		if (role >= 0) {
			this.role = role;
		}
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public void addObserver(IObserver o) {
		if(o!=null) {
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(IObserver o) {
		if(o!=null){
			observers.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		for (IObserver observer : observers) {
			observer.handleEvent();
		}
	}
}
