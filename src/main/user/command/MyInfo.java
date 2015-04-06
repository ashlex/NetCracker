package main.user.command;

import main.command.AbstractCommandBase;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This command working only for console.
 */
public class MyInfo extends AbstractCommandBase {

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
		if (this.isHelp()) {
			return executeResult;
		}
		executeResult.setResult(ExecuteResult.SUCCESS);
		ArrayList<String> info = new ArrayList<String>(5);
		info.add("NickName: " + context.getNickname());
		info.add("Name: " + context.getName());
		info.add("Role: " + context.getRole());
		info.add("Locale: " + context.getLocale());
		if (attributes.getAllAttribute() != null && attributes.getAllAttribute().containsKey("p")) {
			info.add("Password: " + context.getPassword());
		}
		executeResult.setResponse(new Response(info));
		return executeResult;
	}
}
