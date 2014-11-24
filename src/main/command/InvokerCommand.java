package main.command;

import java.io.IOException;
import java.util.logging.Logger;

public class InvokerCommand {

	Logger log=Logger.getLogger(InvokerCommand.class.getName());

	public void storeAndExecute(ICommand command){
		try {
			command.execute();
			command.reset();
		} catch (IOException e) {
			log.info(e.getMessage());
		}

	}


}
