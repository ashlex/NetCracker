package main.console;

import main.command.ICommand;
import main.command.InvokerCommand;
import main.entity.User;
import main.entity.UserContext;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
	private final String INVITE="$:";
	private PrintStream ps = System.out;
	private Scanner scn = new Scanner(System.in);
	private String input;
	private ICommand com;
	private String comName;
	private String[] command;
	private String[] comAttribute;
	private UserContext context;
	private User user;
	private InvokerCommand invokerCommand = new InvokerCommand();
	public void start() {
		ps.println("Добро пожаловать.");
		context=new UserContext();
		user=new User(context);
		while (true) {
			ps.println();
			ps.print(user+INVITE);
			input = scn.nextLine();
			command = input.split(" ");
			comName = command[0];
			comAttribute = new String[command.length - 1];
			System.arraycopy(command, 1, comAttribute, 0, comAttribute.length);
			comName = capitalize(comName);
			invokerCommand.storeAndExecute(com);

		}
	}
	private String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
