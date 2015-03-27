package main.user.entity;

public class User{
	private UserContext context;

	public User(UserContext context) {
		this.context=context;
	}

	public UserContext getContext(){
		return this.context;
	}
	@Override
	public String toString() {
		return context.getNickname();
	}
}
