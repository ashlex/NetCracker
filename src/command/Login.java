package command;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Login implements ICommand {
	private String login=null;
	private String password=null;

	@Override
	public void setOutPutStream(OutputStream request) {

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {

	}

	@Override
	public void setAttributes(String[] attributes) {

	}

	@Override
	public void exec() throws IOException {

	}
}
