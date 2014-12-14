package main.command.system;

import main.command.ICommand;

import java.io.IOException;

public class Exit implements ICommand {
	private String alias;

	public Exit(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean execute() throws IOException {
		System.exit(0);
		return true;
	}

	@Override
	public String getAlias() {
		return null;
	}
}
