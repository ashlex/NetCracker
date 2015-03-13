package main.command.user;

import main.command.ExecuteResult;

import java.io.IOException;
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
				executeResult.setMessage(getHelp());
				log.fine("Calling getHelp.");
				return executeResult;
			}
		executeResult.setResult(ExecuteResult.SUCCESS);
		String info="";
		info+="NickName: "+context.getNickname()+"\n\r";
		info+="Name: "+context.getName()+"\n\r";
		info+="Role: "+context.getRole()+"\n\r";
		info+="Locale: "+context.getLocale()+"\n\r";
		if(attributes!=null && attributes.contains("p")) {
			info += "Password: " + context.getPassword()+"\n\r";
		}
		executeResult.setMessage(info);
		return executeResult;
	}

	@Override
	public String getHelp() {
		return "{-p | -help | -?}";
	}
}
