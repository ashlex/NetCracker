package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public class Login implements ICommand {
	private UserContext context;

	public Login(UserContext context){
		this.context=context;
	}


	@Override
	public void setContext(UserContext context) {

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {

	}
	@Override
	public void execute() throws IOException {
	}

	@Override
	public void reset() {

	}
}
