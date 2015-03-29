package main.command;

import main.command.entity.ExecuteResult;
import main.command.entity.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MacroCommand implements ICommand {
	private List<String> aliasCommands;
	private String alias;
	private CommandBuilder commandBuilder;
	Logger log = Logger.getLogger(this.getClass().getName());
	private ExecuteResult executeResult;
	private ResourceBundle message;

	public MacroCommand(String alias, CommandBuilder commandBuilder) {
		aliasCommands = new ArrayList<String>();
		this.alias = alias;
		this.commandBuilder = commandBuilder;
		message = ResourceBundle.getBundle("main.resources.locale.message");
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
	}

	public MacroCommand(String alias, CommandBuilder commandBuilder, ResourceBundle message) {
		aliasCommands = new ArrayList<String>();
		this.alias = alias;
		this.commandBuilder = commandBuilder;
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
		this.message = message;
	}

	public MacroCommand add(ICommand command) {
		aliasCommands.add(command.getAlias());
		return this;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		ICommand command = null;
		String alias = "";
		for (Iterator<String> iterator = aliasCommands.iterator(); iterator.hasNext(); ) {
			alias = iterator.next();
			command = commandBuilder.getCommand(alias);
			if (command != null) {
				ExecuteResult result = command.execute();
				if (result.getResult() == ExecuteResult.FAIL) {
					executeResult.setResult(ExecuteResult.FAIL, new Response(executeResult.getResponse().toString() +
							" " + result.getCommand().getAlias() + " " + result.getResponse().toString()));
					log.severe(executeResult.getResponse().toString());
					return executeResult;
				} else if (result.getResult() == ExecuteResult.WARNING) {
					String str = "";
					Response tmp = executeResult.getResponse();
					if (tmp != null) {
						str += tmp.toString();
					}
					str += " " + result.getCommand().getAlias();
					str += " " + result.getResponse().toString();
					executeResult.setResult(ExecuteResult.WARNING, new Response(str));
				}
			} else {
				executeResult.setResult(ExecuteResult.FAIL, new Response(executeResult.getResponse().toString() + message.getString("COMMAND_NO_FOUND") + " -->" + iterator.next()));
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
