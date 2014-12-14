package main.command.user;

import java.io.IOException;

public class SaveUser extends AbstractCommandOnUser {

	public SaveUser(String alias) {
		super(alias);
	}

	@Override
	public boolean execute() throws IOException {
		return false;
	}

}
