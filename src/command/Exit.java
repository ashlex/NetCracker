package command;

import entity.UsersBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Exit implements ICommand {
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
		if(UsersBuffer.getInstance().getUser().getOline()) {
			try {
				ICommand com = (ICommand) Class.forName("command.Logout").newInstance();
				com.exec();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
}
