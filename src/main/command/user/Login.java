package main.command.user;

import main.entity.UserContext;

import java.io.IOException;

public class Login extends AbstractCommandOnUser {

	public Login(String alias) {
		super(alias);
	}

	@Override
	public boolean execute() throws IOException {
		if(attributes!=null) {
			if (attributes.size() >= 2) {
				UserContext userContext = daoFactory.getDaoUserContext().getUser(attributes.get(0));
				if (userContext != null) {
					if (userContext.getPassword().equals(attributes.get(1))) {
						context.setNickname(userContext.getNickname());
						context.setName(userContext.getName());
						context.setRole(userContext.getRole());
						context.setPassword(userContext.getPassword());
						context.setOnline(true);
						daoFactory.getDaoUserContext().update(context);
						context.notifyObserver();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String getHelp() {
		return "[]";
	}
// dfgs
}
