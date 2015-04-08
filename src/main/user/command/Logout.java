package main.user.command;

import main.command.AbstractCommandBase;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;

import java.io.IOException;
import java.util.ResourceBundle;

public class Logout extends AbstractCommandBase {

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
//		log.fine(context.toString());
		if(attributes!=null) {
			if (isHelp()) {
				log.finer("Calling getHelp.");
				return new ExecuteResult(this, ExecuteResult.GET_HELP, new Response(getHelp()));
			}
		}
		if(context.isOnline()) {
			context.setOnline(false);
			if(!daoFactory.getDaoUserContext().update(context)){
				executeResult.setResult(ExecuteResult.FAIL);
				executeResult.setResponse(new Response(resourceBundle.getString("RECORDING_FAILED")));
				context.setOnline(true);
				log.severe(context.getNickname() + " " + resourceBundle.getString("RECORDING_FAILED"));
				return executeResult;
			}
			context.reset();
			context.notifyObserver();
			executeResult.setResult(ExecuteResult.SUCCESS);
			executeResult.setResponse(new Response(resourceBundle.getString("GOOD_BY")));
			log.fine("User logout.");
			log.fine(context.toString());
		} else {
			executeResult.setResult(ExecuteResult.WARNING, new Response(resourceBundle.getString("NO_AUTHORISATION")));
			log.fine("Trying logout failed, because you are not logged in.");
		}
		return executeResult;
	}
}
