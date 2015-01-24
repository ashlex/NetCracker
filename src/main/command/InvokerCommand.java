package main.command;

import main.command.user.AbstractCommandOnUser;
import main.entity.CommandHistory;

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
			if(result.getResult()==ExecuteResult.FAIL ){
				log.fine("Execute command "+command.getAlias()+" is failed.");
			}
			if(command instanceof AbstractCommandOnUser){
				((AbstractCommandOnUser)command).reset();
			}
			return result;
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return result;
	}


}
