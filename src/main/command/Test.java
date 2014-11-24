package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public class Test implements ICommand {
	private UserContext context;
	private ArrayList<String> attributes;
	@Override
	public void setContext(UserContext context) {
		if (context != null) {
			this.context=context;
		}
	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if (attributes != null) {
			this.attributes=attributes;
		}
	}

	@Override
	public void execute() throws IOException {
		String str="root";
		if (attributes!=null){
			str=attributes.get(0);
		}
		context.setNickname(str);
		context.notifyObserver();
	}

	@Override
	public void reset() {
		context=null;
		attributes=null;
	}
}
