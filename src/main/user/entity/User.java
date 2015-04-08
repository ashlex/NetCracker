package main.user.entity;

public class User{
	private UserContext context;

	public User(UserContext context) {
		this.context=context;
	}

	public UserContext getContext(){
		return this.context;
	}

	public int getRole(){
		return context.getRole();
	}

	public String getCurrentTopic(){
		if(context.getCurrentTopic()!=null){
			return context.getCurrentTopic().getHeader();
		}
		return "";
	}

	@Override
	public String toString() {
		String str=context.getNickname();
		if (!getCurrentTopic().isEmpty()) {
			str += "->" + getCurrentTopic();
		}
		return str;
	}
}
