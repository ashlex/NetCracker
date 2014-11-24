package main.command;

import main.entity.UserContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private Map<String, ICommand> commands = new HashMap<String, ICommand>();
	private ArrayList<String> attributes;
	private String commandName;
	private UserContext context;

	public void setReceiver(UserContext receiver){
		this.context=receiver;
	}

	/**
	 * Возращает комаду с загруженными параметрами
	 * @param commandLine строка введённая с консоли
	 * @return {@link main.command.ICommand} Объект команды готовой к выполнению
	 */
	public ICommand getCommand(String commandLine){
		parseLine(commandLine);
		ICommand tmp=commands.get(commandName);
		if (attributes!=null){
			tmp.setAttributes(attributes);
		}
		if (context != null) {
			tmp.setContext(context);
		}

		return tmp;
	}

	/**
	 * Добавление команды в список доступных
	 * @param commandName тег по которому будет вызываться команда
	 * @param command конкретная комманда
	 */
	public void addCommand(String commandName, ICommand command){
		if (command != null) {
			this.commands.put(commandName,command);
		}
	}

	/**
	 * Разбирает строку введённую в консоли на тег команды и аргументы
	 * @param line строка введённая в консоль
	 */
	private void parseLine(String line){
		String tmp[]=line.split("\\s+");
		commandName=tmp[0].toLowerCase();
		if(tmp.length>1){
			attributes=new ArrayList<String>(tmp.length-1);
			for (int i = 1; i < tmp.length; i++) {
				attributes.add(tmp[i]);
			}
		}else{
			attributes=null;
		}
	}
}
