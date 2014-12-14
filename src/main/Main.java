package main;

import main.command.*;
import main.command.system.Exit;
import main.command.user.*;
import main.dao.DaoFactory;
import main.dao.FileDaoUserContext;
import main.dao.IDaoFactory;
import main.dao.IDaoUserContext;
import main.entity.CommandHistory;
import main.entity.CommandHistoryElement;
import main.entity.User;
import main.entity.UserContext;

import java.io.*;
import java.util.ResourceBundle;

public class Main {
	public static void main(String[] args) {
//		test();
		init();

	}

	private static void test() {
		ICommand c = new Exit("exit");
		System.out.println(c instanceof ICommand);
		System.out.println(c instanceof ICommandUndo);
		System.out.println(c instanceof AbstractCommandOnUser);
		System.out.println(c instanceof AbstractCommandUndoOnUser);
	}

	private static void init() {
		UserContext context = new UserContext(); //создаём контекст пользователя
		User user = new User(context); // создаём пользователя и передаём ему контекст
		IView view = new ViewConsole(System.out, System.in, ResourceBundle.getBundle("main.resources.locale.message")); // создаём представление пользователя
		context.addObserver(view); // добавляем наблюдателя за контекстом
		CommandHistory<CommandHistoryElement> commandHistory = new CommandHistory<CommandHistoryElement>();
		InvokerCommand invokerCommand = new InvokerCommand(commandHistory);
		IDaoFactory daoFactory = new DaoFactory();
		String path = Main.class.getResource("resources/UserContext").getPath();
		File fileUserContext = new File(path);
		IDaoUserContext daoUserContext = new FileDaoUserContext(fileUserContext);
		daoFactory.setDaoUserContext(daoUserContext);

		CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.setDaoFactory(daoFactory);
		commandBuilder.setReceiver(context);
		ICommand commands[] = {
				new Test("test"),
				new Login("login"),
				new Logout("logout"),
				new Registration("registration"),
				new Exit("exit"),};
		for (ICommand command : commands) {
			commandBuilder.addCommand(command.getAlias(), command);
		}
		view.setCommandBuilder(commandBuilder);
		view.setInvokerCommand(invokerCommand);
		view.setUser(user);
		view.handle();
	}

}
