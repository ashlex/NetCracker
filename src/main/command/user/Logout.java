package main.command.user;

import main.command.ExecuteResult;

import java.io.IOException;

public class Logout extends AbstractCommandOnUser {

	public Logout(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		context.setOnline(false);
		daoFactory.getDaoUserContext().update(context);
		context.reset();
		context.notifyObserver();
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
