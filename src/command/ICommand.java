package command;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public interface ICommand {
	public void setOutPutStream(OutputStream request);
	public void setAttributes(ArrayList<String> attributes);
	public void setAttributes(String[] attributes);
	public void exec() throws IOException;
}
