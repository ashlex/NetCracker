package main.command;

import main.entity.UserContext;

import java.io.IOException;

public class Registration extends CommandBase implements ICommand {

	@Override
	public void execute() throws IOException{
		UserContext userContext=new UserContext();
		userContext.setNickname(attributes.get(0));
		userContext.setPassword(attributes.get(1));
		userContext.setOnline(false);
		userContext.setRole(2);
		daoFactory.getDaoUserContext().add(userContext);
	}
}
