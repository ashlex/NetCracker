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
	public boolean execute() throws IOException {
		boolean res=true;
		for(Iterator<ICommand> iterator=commands.iterator();iterator.hasNext();){
			if(!iterator.next().execute()){
				res=false;
			}
		}
		return res;
	}

	public String getAlias() {
		return this.alias;
	}
}