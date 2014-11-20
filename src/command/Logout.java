package command;

import dao.DaoFactory;
import dao.IDaoUser;
import entity.User;
import entity.UsersBuffer;

import java.io.OutputStream;
import java.util.ArrayList;

public class Logout implements ICommand{


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
	public void exec() {
		IDaoUser d= DaoFactory.getInstance().getFileDaoUser();
		User u= UsersBuffer.getInstance().getUser();
		u.setOnline(false);
		d.update(u);
		u.reset();
	}
}
