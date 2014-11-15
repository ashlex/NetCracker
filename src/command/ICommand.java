package command;

import java.io.OutputStream;

public interface ICommand {
	public void execute(String []args,OutputStream request);
}
