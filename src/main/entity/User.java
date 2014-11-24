package main.entity;

public class User {
	private UserContext context;

	public User(UserContext context) {
		this.context=context;
	}

	@Override
	public String toString() {
		return context.getNickname();
	}
}
