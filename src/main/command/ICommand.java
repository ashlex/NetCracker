package main.command;

import java.io.IOException;

public interface ICommand{
    public boolean execute() throws IOException;
	public String getAlias();
    public String getHelp();
}
