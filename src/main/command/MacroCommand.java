package main.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class MacroCommand implements ICommand {
	private List<ICommand> commands;
	private String alias;
	Logger log=Logger.getLogger(this.getClass().getName());
	private ExecuteResult executeResult;

	public MacroCommand(String alias){
		commands=new ArrayList<ICommand>();
		this.alias=alias;
		this.executeResult=new ExecuteResult(this, ExecuteResult.FAIL,"");
	}

	public MacroCommand add(ICommand command){
		commands.add(command);
		return this;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		for(Iterator<ICommand> iterator=commands.iterator();iterator.hasNext();){
			ExecuteResult result=iterator.next().execute();
			if(result.getResult()==ExecuteResult.FAIL){
				executeResult.setResult(ExecuteResult.FAIL, executeResult.getMessage()+
						" "+result.getCommand().getAlias()+" "+result.getMessage());
				log.severe(executeResult.getMessage());
				return executeResult;
			}else if(result.getResult()==ExecuteResult.WARNING){
				executeResult.setResult(ExecuteResult.WARNING,executeResult.getMessage()+
						" "+result.getCommand().getAlias()+" "+result.getMessage());
			}
		}
		return null;
	}

	public String getAlias() {
		return this.alias;
	}

	@Override
	public String getHelp() {
		return null;
	}
}
