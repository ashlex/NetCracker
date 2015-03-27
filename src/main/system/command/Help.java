package main.system.command;

import main.command.CommandBuilder;
import main.command.ExecuteResult;
import main.user.command.AbstractCommandOnUser;
import main.dao.IDaoCommandHelp;

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
						executeResult.setMessage(commandHelp.getFullHelp());
					}
				} else {
					executeResult.setResult(ExecuteResult.FAIL);
					executeResult.setMessage(resourceBundle.getString("ILLEGAL_ARGUMENT"));
					log.fine("Count of attributes is more than one.");
				}
			}else{
				ArrayList<CommandHelp> commandHelps = daoCommandHelp.getHelps(commandBuilder.getCommandsArray());
				if (commandHelps != null) {
					String message="";
					for (CommandHelp commandHelp : commandHelps) {
						message+=String.format("%1$-18s%2$s%n",commandHelp.getCommand().toUpperCase() ,commandHelp.getShortHelp());
					}
					executeResult.setResult(ExecuteResult.SUCCESS);
					executeResult.setMessage(message);
				}else{
					executeResult.setResult(ExecuteResult.FAIL);
					executeResult.setMessage(resourceBundle.getString("ERROR_GETTING_LIST_TO_COMMANDS"));
					log.info(String.format(TEMPLATE, "commandHelps"));
				}
			}
		}else{
			executeResult.setResult(ExecuteResult.FAIL);
			executeResult.setMessage(resourceBundle.getString("ERROR_GETTING_LIST_TO_COMMANDS"));
			log.info(String.format(TEMPLATE, "DAOCommandHelp"));
		}
		return executeResult;
	}
}
