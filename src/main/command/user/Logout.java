package main.command.user;

import main.command.ExecuteResult;

import java.io.IOException;
import java.util.ResourceBundle;

public class Logout extends AbstractCommandOnUser {

	public Logout(){
		super("logout");
	}
	public Logout(String alias) {
		super(alias);
	}

	public Logout(String alias, ResourceBundle resourceBundle) {
		super(alias, resourceBundle);
	}

	public Logout(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
		super(alias, resourceBundle, executeResult);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		if(context.isOnline()) {
			context.setOnline(false);
			if(!daoFactory.getDaoUserContext().update(context)){
				executeResult.setResult(ExecuteResult.FAIL);
				executeResult.setMessage(resourceBundle.getString("RECORDING_FAILED"));
				context.setOnline(true);
				return executeResult;
			}
			executeResult.setResult(ExecuteResult.SUCCESS);
			executeResult.setMessage("Good by");
			context.reset();
			context.notifyObserver();
		}
		return executeResult;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
