package main.dao;

import main.command.ICommand;
import main.system.entity.CommandHelp;

import java.util.ArrayList;

/**
 * This interface gives functionality for get information on commands.
 */

public interface IDaoCommandHelp {
	public CommandHelp getHelp(ICommand command);
	public CommandHelp getHelp(String alias);
	public ArrayList<CommandHelp> getHelps(String []aliases);
	public ArrayList<CommandHelp> getHelps(ArrayList<ICommand> aliases);
}
