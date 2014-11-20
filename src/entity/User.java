package entity;

import dao.DaoFactory;
import dao.IDaoUser;

public class User {
	private String aLogin = null;
	private String aPhone = null;
	private String aName = null;
	private String aPassword = null;
	private boolean online = false;
	private long id;


	private int aRole;

	public User(String login, String password) {
		id = System.currentTimeMillis();
		this.aLogin = login;
		this.aPassword = password;
	}

	public User() {
		id = System.currentTimeMillis();
		aRole = 0;
	}

	public void setRole(int aRole) {
		this.aRole = aRole;
	}

	public void setName(String aName) {
		this.aName = aName;
	}

	public void setPhone(String aPhone) {
		this.aPhone = aPhone;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getLogin() {
		return aLogin;
	}

	public String getName() {
		return aName;
	}

	public String getPhone() {
		return aPhone;
	}

	public boolean getOline() {
		return online;
	}

	public String getPassword() {
		return aPassword;
	}

	public int getRole() {
		return aRole;
	}

	public boolean verification(String aPassword) {
		return this.aPassword.equals(aPassword);
	}

	public void update(User u) {
		aLogin = u.getLogin();
		aPassword = u.getPassword();
		aPhone = u.getPhone();
		aName = u.getName();
		aRole = u.getRole();
		online = u.getOline();
	}
	public  void reset(){
		this.aLogin = null;
		this.aPhone = null;
		this.aName = null;
		this.aPassword = null;
		this.online = false;
		this.aRole=0;
	}

	@Override
	public String toString() {
		return "login:" + aLogin + " password:" + aPassword + " name:" + aName + " phone:" + aPhone + " online:" + online + " role:" + aRole + " " + id;
	}

	@Override
	protected void finalize() throws Throwable {
		IDaoUser d = DaoFactory.getInstance().getFileDaoUser();
		this.online = false;
		d.update(this);
		super.finalize();
	}
}
