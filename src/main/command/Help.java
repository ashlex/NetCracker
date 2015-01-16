package main.command;


import main.command.user.AbstractCommandOnUser;

import java.io.IOException;


public class Help extends AbstractCommandOnUser {

	public Help(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
