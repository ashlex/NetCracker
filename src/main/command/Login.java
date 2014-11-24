package main.command;

import main.entity.UserContext;

import java.io.IOException;

public class Login extends CommandBase implements ICommand {

	@Override
	public void execute() throws IOException {
		if (attributes.size()>=2) {
			UserContext userContext = daoFactory.getDaoUserContext().getUser(attributes.get(0));
			if (userContext.getPassword().equals(attributes.get(1))) {
				context.setNickname(userContext.getNickname());
				context.setName(userContext.getName());
				context.setRole(userContext.getRole());
				context.setPassword(userContext.getPassword());
				context.setOnline(true);
				daoFactory.getDaoUserContext().update(context);
				context.notifyObserver();
			}
		}
	}

}
