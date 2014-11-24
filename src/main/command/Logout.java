package main.command;

import java.io.IOException;

public class Logout extends CommandBase implements ICommand{


	@Override
	public void execute() throws IOException {
		context.setOnline(false);
		daoFactory.getDaoUserContext().update(context);
		context.reset();
		context.notifyObserver();
	}
}
