package main.command;

import main.command.entity.Attributes;
import main.dao.IDaoFactory;
import main.user.entity.UserContext;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandBuilder {
	private Map<String, ICommand> commands = new HashMap<String, ICommand>();
//	private ArrayList<String> attributes;
	private Attributes attributes;
	private String commandName;
	private UserContext context;
	private IDaoFactory daoFactory;
	private IInvoker invoker;
	Logger log = Logger.getLogger(this.getClass().getName());

	public CommandBuilder(){
		invoker=null;
	}

	public void setReceiver(UserContext receiver) {
		log.finer(receiver.toString());
		this.context = receiver;
	}

	public void setDaoFactory(IDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * This function returns the command with parameters
	 *
	 * @param commandLine This is string entered to console
	 * @return {@link main.command.ICommand} Command object ready to run
	 */
	public ICommand getCommand(String commandLine) {
		parseLine(commandLine);
		ICommand command = commands.get(commandName);
		if (command != null) {
			if (command instanceof AbstractCommandBase) {
				if (attributes != null) {
					((AbstractCommandBase) command).setAttributes(attributes);
				}
				if (context != null) {
					((AbstractCommandBase) command).setContext(context);
				}
				if (daoFactory != null) {
					((AbstractCommandBase) command).setDaoFactory(daoFactory);
				}
			} else {
				if (attributes != null) {
					if (command instanceof IContainsAttributes) {
						((IContainsAttributes) command).setAttributes(attributes);
					}
				}
				if(daoFactory!=null){
					if(command instanceof IUseDAO){
						((IUseDAO)command).setDaoFactory(daoFactory);
					}
				}
			}
			if(command instanceof IManagementCommand){
				if(invoker!=null){
					((IManagementCommand) command).setInvoker(invoker);
				}
				((IManagementCommand) command).setCommandBuilder(this);
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
	 * @param command Concrete command
	 */
	public void addCommand(ICommand command) {
		if (command != null) {
			addCommand(command.getAlias(), command);
		}
	}

	public String[] getCommandsArray() {
		String[] aliases = new String[commands.size()];
		int i = 0;
		for (String key : commands.keySet()) {
			aliases[i++] = key;
		}
		return aliases;
	}

	/**
	 * Parses the entered string from the console.
	 *
	 * @param line Text entered from the console.
	 */
	private void parseLine(String line) {
		String tmp[] = line.split("\\s+");
		commandName = tmp[0].toLowerCase();
		attributes=new Attributes(line);
		/*
		if (tmp.length > 1) {
			attributes = new ArrayList<String>(tmp.length - 1);
			for (int i = 1; i < tmp.length; i++) {
				if (tmp[i].startsWith("-")) {
					attributes.add(tmp[i].substring(1));
				}
			}
		} else {
			attributes = null;
		}
		*/
	}

	public void setInvoker(IInvoker invoker) {
		this.invoker = invoker;
	}
}
