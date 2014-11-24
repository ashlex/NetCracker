package main.command;

import java.io.IOException;

public class Exit extends CommandBase implements ICommand {


	@Override
	public void execute() throws IOException {
		context.setOnline(false);
		daoFactory.getDaoUserContext().update(context);
		System.exit(0);
	}
}
