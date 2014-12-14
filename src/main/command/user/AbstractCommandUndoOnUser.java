package main.command.user;

import main.command.ICommandUndo;

public abstract class AbstractCommandUndoOnUser extends AbstractCommandOnUser implements ICommandUndo {

	public AbstractCommandUndoOnUser(String alias) {
		super(alias);
	}
}
