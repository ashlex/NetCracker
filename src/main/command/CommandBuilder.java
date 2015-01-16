package main.command;

import main.IObservable;
import main.IObserver;
import main.command.user.AbstractCommandOnUser;
import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandBuilder implements IObservable {
	private Map<String, ICommand> commands = new HashMap<String, ICommand>();
	private ArrayList<String> attributes;
	private String commandName;
	private UserContext context;
	private IDaoFactory daoFactory;

	public void setReceiver(UserContext receiver) {
		this.context = receiver;
	}

	public void setDaoFactory(IDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * This function return the command with parameters
	 *
	 * @param commandLine This is string entered to console
	 * @return {@link main.command.ICommand} Command object ready to run
	 */
	public ICommand getCommand(String commandLine) {
		parseLine(commandLine);
		ICommand command = commands.get(commandName);
		if (command != null) {
			if (command instanceof AbstractCommandOnUser) {
				if (attributes != null) {
					((AbstractCommandOnUser)command).setAttributes(attributes);
				}
				if (context != null) {
					((AbstractCommandOnUser)command).setContext(context);
				}
				if (daoFactory != null) {
					((AbstractCommandOnUser)command).setDaoFactory(daoFactory);
				}
			}
		}
		return command;
	}

	/**
	 * This function add a command in the list
	 *
	 * @param commandName This is tag for execute command
	 * @param command     Concrete command
	 */
	public void addCommand(String commandName, ICommand command) {
		if (command != null) {
			this.commands.put(commandName, command);
		}
	}

	/**
	 * This function add a command in the list.
	 *
	 * @param command     Concrete command
	 */
	public void addCommand(ICommand command) {
		if (command != null) {
			addCommand(command.getAlias(), command);
		}
	}

	/**
	 * Parses the entered string from the console.
	 *
	 * @param line Text entered from the console.
	 */
	private void parseLine(String line) {
		String tmp[] = line.split("\\s+");
		commandName = tmp[0].toLowerCase();
		if (tmp.length > 1) {
			attributes = new ArrayList<String>(tmp.length - 1);
			for (int i = 1; i < tmp.length; i++) {
				if(tmp[i].startsWith("-")){
					attributes.add(tmp[i].substring(1));
				}
			}
		} else {
			attributes = null;
		}
	}

	@Override
	public void addObserver(IObserver o) {

	}

	@Override
	public void removeObserver(IObserver o) {

	}

	@Override
	public void notifyObserver() {

	}
}
