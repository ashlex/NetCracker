package command;

import controller.Authentication;

import java.io.OutputStream;

public class Login implements ICommand {
	private String login=null;
	private String password=null;

	@Override
	public void execute(String []args,OutputStream request) {
		if(args[0] instanceof String){
			login=args[0];
		}
		if(args[1] instanceof String){
			password=args[1];
		}
		Authentication.logIn(login,password);
	}
}
