package main.user.command;

import main.command.entity.ExecuteResult;
import main.command.entity.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This command working only for console.
 */
public class MyInfo extends AbstractCommandOnUser {

	public MyInfo(String alias) {
		super(alias);
	}

	public MyInfo(String alias, ResourceBundle resourceBundle) {
		super(alias, resourceBundle);
	}

	public MyInfo(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
		super(alias, resourceBundle, executeResult);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		if (attributes != null && (attributes.contains("help") || attributes.contains("?"))) {
				executeResult.setResult(ExecuteResult.GET_HELP);
				executeResult.setResponse(new Response(getHelp()));
				log.fine("Calling getHelp.");
				return executeResult;
			}
		executeResult.setResult(ExecuteResult.SUCCESS);
		ArrayList<String> info=new ArrayList<String>(5);
		info.add("NickName: "+context.getNickname()+"\n\r");
		info.add("Name: "+context.getName()+"\n\r");
		info.add("Role: "+context.getRole()+"\n\r");
		info.add("Locale: "+context.getLocale()+"\n\r");
		if(attributes!=null && attributes.contains("p")) {
			info.add("Password: " + context.getPassword()+"\n\r");
		}
		executeResult.setResponse(new Response(info));
		return executeResult;
	}
}
