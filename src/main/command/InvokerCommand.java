package main.command;

import main.command.user.AbstractCommandOnUser;
import main.entity.CommandHistory;
import main.entity.CommandHistoryElement;

import java.io.IOException;
import java.util.logging.Logger;

public class InvokerCommand {
	private CommandHistory commandHistory;
	Logger log=Logger.getLogger(InvokerCommand.class.getName());

	public InvokerCommand(CommandHistory commandHistory){
		this.commandHistory=commandHistory;
	}

	public ExecuteResult storeAndExecute(ICommand command, IPerformer performer){
		ExecuteResult result=null;
		try {

			result=command.execute();
//			commandHistory.add();

			if(command instanceof AbstractCommandOnUser){
				((AbstractCommandOnUser)command).reset();
			}
			return result;
//			log.info(commandHistory.getLast().toString());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return result;
	}


}
