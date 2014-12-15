package main.command;

import main.command.user.AbstractCommandOnUser;
import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandBuilder {
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
	 * Возращает комаду с загруженными параметрами
	 *
	 * @param commandLine строка введённая с консоли
	 * @return {@link main.command.ICommand} Объект команды готовой к выполнению
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
	 * Добавление команды в список доступных
	 *
	 * @param commandName тег по которому будет вызываться команда
	 * @param command     конкретная комманда
	 */
	public void addCommand(String commandName, ICommand command) {
		if (command != null) {
			this.commands.put(commandName, command);
		}
	}

	/**
	 * Добавление команды в список доступных
	 *
	 * @param command     конкретная комманда
	 */
	public void addCommand(ICommand command) {
		if (command != null) {
			this.commands.put(command.getAlias(), command);
		}
	}

	/**
	 * Разбирает строку введённую в консоли на тег команды и аргументы
	 *
	 * @param line строка введённая в консоль
	 */
	private void parseLine(String line) {
		String tmp[] = line.split("\\s+");
		commandName = tmp[0].toLowerCase();
		if (tmp.length > 1) {
			attributes = new ArrayList<String>(tmp.length - 1);
			for (int i = 1; i < tmp.length; i++) {
				attributes.add(tmp[i]);
			}
		} else {
			attributes = null;
		}
	}
}
