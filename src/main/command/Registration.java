package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public class Registration implements ICommand {
	private ArrayList<String> attributes=null;


	@Override
	public void setContext(UserContext context) {

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if(attributes!=null){
			this.attributes=attributes;
		}
	}


	@Override
	public void execute() throws IOException{
	}

	@Override
	public void reset() {

	}
}
