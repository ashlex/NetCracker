package command;

import entity.User;

import java.io.OutputStream;
import java.io.PrintStream;

public class Registration implements ICommand {

	@Override
	public void execute(String[] args, OutputStream request) {
		PrintStream printStream = (PrintStream) request;
		switch (args.length) {
			case 1:
				try {
					User u = controller.Registration.newUser(args[0]);
					printStream.println(u);
				} catch (IllegalArgumentException e) {
					e.printStackTrace(printStream);
				}
				break;
			case 2:
				try {
					User u = controller.Registration.newUser(args[0],args[1]);
					printStream.println(u);
				} catch (IllegalArgumentException e) {
					e.printStackTrace(printStream);
				}
				break;
			default:
				printStream.println("Input please login and password");
		}
	}
}
