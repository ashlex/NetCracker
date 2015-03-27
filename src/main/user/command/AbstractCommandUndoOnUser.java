package main.user.command;

import main.command.ICommandUndo;

public abstract class AbstractCommandUndoOnUser extends AbstractCommandOnUser implements ICommandUndo {

	public AbstractCommandUndoOnUser(String alias) {
		super(alias);
	}
}
