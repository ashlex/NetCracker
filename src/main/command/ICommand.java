package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public interface ICommand {
	public void setContext(UserContext context);
	public void setAttributes(ArrayList<String> attributes);
	public void execute() throws IOException;
	public void reset();
}
