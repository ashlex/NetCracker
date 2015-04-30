package main.dao.hibernate;

import main.command.ICommand;
import main.dao.IDaoCommandHelp;
import main.system.entity.CommandHelp;

import java.util.ArrayList;

public class DaoCommand implements IDaoCommandHelp {
	@Override
	public CommandHelp getHelp(ICommand command) {
		return null;
	}

	@Override
	public CommandHelp getHelp(String alias) {
		return null;
	}

	@Override
	public ArrayList<CommandHelp> getHelps(String[] aliases) {
		return null;
	}

	@Override
	public ArrayList<CommandHelp> getHelps(ArrayList<ICommand> aliases) {
		return null;
	}
}
