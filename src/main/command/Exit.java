package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public class Exit implements ICommand {

	@Override
	public void setContext(UserContext context) {

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {

	}

	@Override
	public void execute() throws IOException {
		System.exit(0);
	}

	@Override
	public void reset() {

	}
}
