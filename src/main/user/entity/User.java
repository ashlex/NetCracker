package main.user.entity;

import java.util.ResourceBundle;

public class User{
	private UserContext context;
	private ResourceBundle general;

	public User(UserContext context) {
		this.context=context;
		general= ResourceBundle.getBundle("main.resources.locale.general");
	}
/*
	public UserContext getContext(){
		return this.context;
	}
*/
	public int getRole(){
		return context.getRole();
	}

	public String getCurrentTopicHeader(){
		if(context.getCurrentTopic()!=null){
			return context.getCurrentTopic().getHeader();
		}
		return "";
	}

	@Override
	public String toString() {
		String str=context.getNickname();
		if(str==null){
			str=general.getString("USER_ANONYMOUS");
		}
		if (!getCurrentTopicHeader().isEmpty()) {
			str += "->" + getCurrentTopicHeader();
		}
		return str;
	}
}
