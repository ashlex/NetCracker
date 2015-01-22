package main;

import main.command.*;
import main.command.system.Exit;
import main.command.user.Login;
import main.command.user.Logout;
import main.command.user.Registration;
import main.command.user.SaveUser;
import main.dao.DaoFactory;
import main.dao.FileDaoUserContext;
import main.dao.IDaoFactory;
import main.dao.IDaoUserContext;
import main.entity.CommandHistory;
import main.entity.CommandHistoryElement;
import main.entity.User;
import main.entity.UserContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.LogManager;

public class Main {
	public static void main(String[] args) {
		init();

	}

	private static void test() {
		ArrayList<String> test=new ArrayList<String>();
		test.add("fdgsd");
		test.add("get");
		test.add("set");
		test.add("root");
		test.add("this");
		System.out.println(test.contains("get"));
		System.out.println(test.contains("get1"));

	}

	private static void init() {
		configureLog();

		UserContext context = new UserContext();
		User user = new User(context);
		Locale l=new Locale("en","US");
		IView view = new ViewConsole(System.out, System.in); // This create new view.
		context.addObserver(view); // Here we add observer for context.
		CommandHistory<CommandHistoryElement> commandHistory = new CommandHistory<CommandHistoryElement>();
		InvokerCommand invokerCommand = new InvokerCommand(commandHistory);
		IDaoFactory daoFactory = new DaoFactory();
		String path = Main.class.getResource("resources/UserContext").getPath();
		File fileUserContext = new File(path);
		path=Main.class.getResource("resources/CommandHelp").getPath();
		File fileCommandHelp = new  File(path);
		IDaoUserContext daoUserContext = new FileDaoUserContext(fileUserContext);
		daoFactory.setDaoUserContext(daoUserContext);

		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.setDaoFactory(daoFactory);
		commandBuilder.setReceiver(context);
		createCommands(commandBuilder);

		view.setCommandBuilder(commandBuilder);
		view.setInvokerCommand(invokerCommand);
		view.setUser(user);
		view.handle();
	}

	private static CommandBuilder createCommands(CommandBuilder commandBuilder) {
		ICommand commands[] ={
				new Test("test"),
				new Login("login"),
				new Logout("logout"),
				new Registration("registration"),
				new SaveUser("saveuser"),
		};
		for (ICommand command : commands){
			commandBuilder.addCommand(command);
		}

		commandBuilder.addCommand(new MacroCommand("exit")
						.add(commandBuilder.getCommand("logout"))
						.add(new Exit("exit"))
		);

		return commandBuilder;
	}
	private static void configureLog(){
		try {
			LogManager.getLogManager().readConfiguration(
					Main.class.getResourceAsStream("resources/log.properties"));
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
