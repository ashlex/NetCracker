package command;

import dao.DaoFactory;
import dao.IDaoUser;
import entity.User;
import entity.UsersBuffer;

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
		if (attributes.length==2) {
			this.login = attributes[0];
			this.password = attributes[1];
		}
	}

	@Override
	public void exec() throws IOException {
		User u=UsersBuffer.getInstance().getUser();
		if (u.getRole()>0||password==null||login==null){
			throw new IOException();
		}
		IDaoUser d= DaoFactory.getInstance().getFileDaoUser();
		User u2=d.getUser(login);
		System.out.println(u2);
		if(u2.verification(password)){
			u2.setOnline(true);
			d.update(u2);
			u.update(u2);
		}
	}
}
