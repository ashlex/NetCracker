package command;

import entity.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Registration implements ICommand {
	private OutputStream outputStream=null;
	private ArrayList<String> attributes=null;
	@Override
	public void setOutPutStream(OutputStream request) {
		if(request instanceof OutputStream){
			this.outputStream=request;
		}
	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if(attributes!=null){
			this.attributes=attributes;
		}
	}

	@Override
	public void setAttributes(String[] attributes) {
		if(attributes.length>0){
			this.attributes=new ArrayList<String>();
			for (String attribute : attributes) {
				this.attributes.add(attribute);
			}
		}
	}

	@Override
	public void exec() throws IOException{
		if(attributes!=null) {
			switch (attributes.size()) {
				case 1:
					try {
						User u = controller.Registration.newUser(attributes.get(0));
						if (outputStream != null) {
							outputStream.write(u.toString().getBytes());
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						User u = controller.Registration.newUser(attributes.get(0), attributes.get(1));
						if (outputStream != null) {
							outputStream.write(u.toString().getBytes());
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					break;
			}
		}else{
			outputStream.write("Input please login and password".getBytes());
		}
	}
}
