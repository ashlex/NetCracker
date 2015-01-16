package main.command.user;

import main.command.ExecuteResult;

import java.io.IOException;

public class SaveUser extends AbstractCommandOnUser {

	public SaveUser(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		System.out.println("Command:"+this.getClass().getName());
		return new ExecuteResult(this,ExecuteResult.SUCCESS,"This user is saving.");
	}

	@Override
	public String getHelp() {
		return null;
	}

}
