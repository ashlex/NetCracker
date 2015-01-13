package main.command.user;

import main.entity.UserContext;

import java.io.IOException;

public class Registration extends AbstractCommandOnUser {


	public Registration(String alias) {
		super(alias);
	}

	@Override
	public boolean execute() throws IOException {
		UserContext userContext = new UserContext();
		userContext.setNickname(attributes.get(0));
		userContext.setPassword(attributes.get(1));
		userContext.setOnline(false);
		userContext.setRole(2);
		return daoFactory.getDaoUserContext().add(userContext);
	}

	@Override
	public String getHelp() {
		return null;
	}

}
