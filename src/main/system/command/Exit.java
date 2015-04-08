package main.system.command;

import main.command.ICommand;
import main.command.entity.ExecuteResult;

import java.io.IOException;

/**
 * This command closes the program.
 */
public class Exit implements ICommand{
	private String alias;

	public Exit(String alias) {
		this.alias = alias;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		System.exit(0);
		return null;
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
