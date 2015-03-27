package main.dao;

import main.command.ICommand;

import java.util.ArrayList;

public interface IDaoCommandHelp {
	public CommandHelp getHelp(ICommand command);
	public CommandHelp getHelp(String alias);
	public ArrayList<CommandHelp> getHelps(String []aliases);
	public ArrayList<CommandHelp> getHelps(ArrayList<ICommand> aliases);
}
