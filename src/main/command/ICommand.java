package main.command;

import main.command.entity.ExecuteResult;

import java.io.IOException;

public interface ICommand{
    public ExecuteResult execute() throws IOException;
	public String getAlias();
    public String getHelp();
}
