package main.command;

import main.command.entity.ExecuteResult;

public interface IInvoker {
	public ExecuteResult storeAndExecute(ICommand command);
}
