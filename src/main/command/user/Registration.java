package main.command.user;

import main.command.ExecuteResult;
import main.entity.UserContext;

import java.io.IOException;

public class Registration extends AbstractCommandOnUser {


	public Registration(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		UserContext userContext = new UserContext();
		userContext.setNickname(attributes.get(0));
		userContext.setPassword(attributes.get(1));
		userContext.setOnline(false);
		userContext.setRole(2);
		daoFactory.getDaoUserContext().add(userContext);
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}

}
