package main.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MacroCommand implements ICommand {
	private List<String> AliasCommands;
	private String alias;
	private CommandBuilder commandBuilder;
	Logger log = Logger.getLogger(this.getClass().getName());
	private ExecuteResult executeResult;
	private ResourceBundle message;

	public MacroCommand(String alias, CommandBuilder commandBuilder) {
		AliasCommands = new ArrayList<String>();
		this.alias = alias;
		this.commandBuilder = commandBuilder;
		message=ResourceBundle.getBundle("main.resources.locale.message");
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "");
	}

	public MacroCommand(String alias, CommandBuilder commandBuilder, ResourceBundle message) {
		AliasCommands = new ArrayList<String>();
		this.alias = alias;
		this.commandBuilder = commandBuilder;
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "");
		this.message=message;
	}

	public MacroCommand add(ICommand command) {
		AliasCommands.add(command.getAlias());
		return this;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		ICommand command = null;
		String alias="";
		for (Iterator<String> iterator = AliasCommands.iterator(); iterator.hasNext(); ) {
			alias=iterator.next();
			command = commandBuilder.getCommand(alias);
			if (command != null) {
				ExecuteResult result =command.execute();
				if (result.getResult() == ExecuteResult.FAIL) {
					executeResult.setResult(ExecuteResult.FAIL, executeResult.getMessage() +
							" " + result.getCommand().getAlias() + " " + result.getMessage());
					log.severe(executeResult.getMessage());
					return executeResult;
				} else if (result.getResult() == ExecuteResult.WARNING) {
					executeResult.setResult(ExecuteResult.WARNING, executeResult.getMessage() +
							" " + result.getCommand().getAlias() + " " + result.getMessage());
				}
			}else{
				executeResult.setResult(ExecuteResult.FAIL, executeResult.getMessage()+ message.getString("COMMAND_NO_FOUND")+" -->"+iterator.next());
			}
		}
		return null;
	}

	public String getAlias() {
		return this.alias;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
