package main.system.command;

import main.command.CommandBuilder;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.dao.IDaoCommandHelp;
import main.system.entity.CommandHelp;
import main.user.command.AbstractCommandOnUser;

import java.io.IOException;
import java.util.ArrayList;

public class Help extends AbstractCommandOnUser {
	private CommandBuilder commandBuilder;

	public Help(String alias, CommandBuilder commandBuilder) {
		super(alias);
		this.commandBuilder = commandBuilder;
	}


	@Override
	public ExecuteResult execute() throws IOException {
		final String TEMPLATE = "Trying get \"%1$s\" is failed. Object \"%1$s\" not set.";
		IDaoCommandHelp daoCommandHelp = daoFactory.getDaoCommandHelp();
		if (daoCommandHelp != null) {
			if (attributes != null){
				if(attributes.size() == 1) {
					CommandHelp commandHelp = daoCommandHelp.getHelp(attributes.get(0));
					if (commandHelp != null) {
						executeResult.setResult(ExecuteResult.SUCCESS);
						executeResult.setResponse(new Response(commandHelp.getFullHelp()));
					}
				} else {
					executeResult.setResult(ExecuteResult.FAIL);
					executeResult.setResponse(new Response(resourceBundle.getString("ILLEGAL_ARGUMENT")));
					log.fine("Count of attributes is more than one.");
				}
			}else{
				ArrayList<CommandHelp> commandHelps = daoCommandHelp.getHelps(commandBuilder.getCommandsArray());
				if (commandHelps != null) {
					ArrayList<String> response=new ArrayList<String>(commandHelps.size());
					for (CommandHelp commandHelp : commandHelps) {
						response.add(String.format("%1$-18s%2$s", commandHelp.getCommand().toUpperCase(), commandHelp.getShortHelp()));
					}
					executeResult.setResult(ExecuteResult.SUCCESS);
					executeResult.setResponse(new Response(response));
				}else{
					executeResult.setResult(ExecuteResult.FAIL);
					executeResult.setResponse(new Response(resourceBundle.getString("ERROR_GETTING_LIST_TO_COMMANDS")));
					log.info(String.format(TEMPLATE, "commandHelps"));
				}
			}
		}else{
			executeResult.setResult(ExecuteResult.FAIL);
			executeResult.setResponse(new Response(resourceBundle.getString("ERROR_GETTING_LIST_TO_COMMANDS")));
			log.info(String.format(TEMPLATE, "DAOCommandHelp"));
		}
		return executeResult;
	}
}
