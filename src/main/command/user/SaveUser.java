package main.command.user;

import java.io.IOException;

public class SaveUser extends AbstractCommandOnUser {

	public SaveUser(String alias) {
		super(alias);
	}

	@Override
	public boolean execute() throws IOException {
		System.out.println("Command:"+this.getClass().getName());
		return true;
	}

	@Override
	public String getHelp() {
		return null;
	}

}
