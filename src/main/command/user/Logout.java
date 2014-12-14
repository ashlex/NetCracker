package main.command.user;

import java.io.IOException;

public class Logout extends AbstractCommandOnUser {

	public Logout(String alias) {
		super(alias);
	}

	@Override
	public boolean execute() throws IOException {
		context.setOnline(false);
		daoFactory.getDaoUserContext().update(context);
		context.reset();
		context.notifyObserver();
		return true;
	}
}
