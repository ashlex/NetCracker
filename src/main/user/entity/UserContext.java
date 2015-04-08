package main.user.entity;

import main.IObservable;
import main.IObserver;
import main.command.IPerformer;
import main.system.entity.Topic;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

public class UserContext implements IObservable, IPerformer {
	private Set<IObserver> observers = new HashSet<IObserver>();
	private String nickname;
	private String password;
	private String name;
	private int role;
	private boolean online;
	private Locale locale;
	private Topic currentTopic;
	private Logger log=Logger.getLogger(this.getClass().getName());


	public UserContext() {
		this.nickname = null;
		this.password = null;
		this.name = null;
		this.role = 0;
		this.online = false;
		this.currentTopic = null;
		this.locale = Locale.getDefault();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if (nickname != null && !nickname.equals("")) {
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

	public Locale getLocale(){
		return this.locale;
	}

	public void setLocale(Locale locale){
		this.locale=locale;
	}

	public void reset() {
		if(nickname==null){
			log.fine("Field \"nickname\" can not be null because already null.");
		}else {
			this.nickname = null;
		}
		if(password==null){
			log.fine("Field \"password\" can not be null because already null.");
		}else {
			this.password = null;
		}
		if(name==null || name.equalsIgnoreCase("null")){
			log.fine("Field \"name\" can not be null because already null.");
		}else {
			this.name = null;
		}
		if(this.role==0){
			log.fine("Field \"role\" can not be 0 because already 0.");
		}else {
			this.role = 0;
		}
		if(!this.online){
			log.fine("Field \"online\" can not be false because already false.");
		}else {
			this.online = false;
		}
	}

	@Override
	public void addObserver(IObserver o) {
		if (o != null) {
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(IObserver o) {
		if (o != null) {
			observers.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		for (IObserver observer : observers) {
			observer.handleEvent();
		}
	}

	@Override
	public String toString() {
		return "UserContext{" +
				"nickname='" + nickname + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", role=" + role +
				", online=" + online +
				", locale=" + locale +
				'}';
	}

	public Topic getCurrentTopic() {
		return currentTopic;
	}

	public void setCurrentTopic(Topic currentTopic) {
		this.currentTopic = currentTopic;
	}
}
