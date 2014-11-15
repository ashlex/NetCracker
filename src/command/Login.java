package command;

import dao.DaoFactory;
import dao.IDaoUser;

import java.io.OutputStream;

public class Login implements ICommand {
	private String login=null;
	private String password=null;
	private IDaoUser iDaoUser;

	@Override
	public void execute(String []args,OutputStream request) {
		if(args[0] instanceof String){
			login=args[0];
		}
		if(args[1] instanceof String){
			password=args[1];
		}
		iDaoUser= DaoFactory.getInstance().getDaoUser("RamDaoUser");
		if()
	}
}
