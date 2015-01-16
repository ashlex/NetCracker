package main.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MacroCommand implements ICommand {
	private List<ICommand> commands;
	private String alias;

	public MacroCommand(String alias){
		commands=new ArrayList<ICommand>();
		this.alias=alias;
	}

	public MacroCommand add(ICommand command){
//		System.out.println(command);
		commands.add(command);
		return this;
	}

	@Override
	public ExecuteResult execute() throws IOException {
		for(Iterator<ICommand> iterator=commands.iterator();iterator.hasNext();){
			ExecuteResult result=iterator.next().execute();
			if(result.getResult()==ExecuteResult.FAIL){
				return new ExecuteResult(this,ExecuteResult.FAIL,result.getMessage());
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
