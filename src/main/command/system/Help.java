package main.command.system;

import main.command.ExecuteResult;
import main.command.user.AbstractCommandOnUser;

import java.io.IOException;
import java.util.ResourceBundle;

public class Help extends AbstractCommandOnUser {


	public Help(String alias) {
		super(alias);
	}

	public Help(String alias, ResourceBundle resourceBundle) {
		super(alias, resourceBundle);
	}

	public Help(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
		super(alias, resourceBundle, executeResult);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		return null;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
