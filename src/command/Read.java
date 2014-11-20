package command;

import dao.FileDaoUser;

import java.io.*;
import java.util.ArrayList;

public class Read implements ICommand {
	OutputStream outputStream=null;

	String path = FileDaoUser.class.getResource("Users").getPath();
	private File uFile=new File(path);

	@Override
	public void setOutPutStream(OutputStream request) {
		if(request!=null) {
			outputStream = request;
		}
	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {

	}

	@Override
	public void setAttributes(String[] attributes) {

	}

	@Override
	public void exec() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(uFile)));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			outputStream.write((line + "\n").getBytes());
			}
	}
}
