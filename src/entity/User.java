package entity;

public class User {
	private String aLogin=null;
	private String aPhone=null;
	private String aName=null;
	private String aPassword=null;
	private boolean online=false;
	private long id;
	private int aRole;

	public User(String login,String password){
		id=System.currentTimeMillis();
		this.aLogin=login;
		this.aPassword=password;
		UsersBuffer.push(this);
	}
	public User(){
		id=System.currentTimeMillis();
		aRole=0;
		UsersBuffer.push(this);
	}

	public long getId() {
		return id;
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

	public void setName(String aName) {
		this.aName = aName;
	}

	public void setPhone(String aPhone) {
		this.aPhone = aPhone;
	}

	@Override
	public String toString() {
		return aLogin+" "+aPassword+" "+aName+" "+aPhone+" "+online;
	}

	public boolean getOline(){
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	public boolean verification(String aPassword){
		return this.aPassword.equals(aPassword);
	}
	public String getPassword(){
		return aPassword;
	}
}
