package main.command.system;

import main.command.ICommand;

import java.io.IOException;

/**
 * This command closes the program.
 */
public class Exit implements ICommand {
	private String alias;

	public Exit(String alias) {
		this.alias = alias;
	}

	@Override
	public boolean execute() throws IOException {
		System.out.println("Command:"+this.getClass().getName());
		System.exit(0);
		return true;
	}

	@Override
	public String getAlias() {
		return this.alias;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
