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

import java.io.File;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("test")) {
				test();
			} else {
				init();
			}
		}else{
			init();
		}

	}

	private static void test() {
		ICommand c = new Exit("exit");
		System.out.println(c instanceof ICommand);
		System.out.println(c instanceof ICommandUndo);
		System.out.println(c instanceof AbstractCommandOnUser);
		System.out.println(c instanceof AbstractCommandUndoOnUser);
		Enumeration e = System.getProperties().propertyNames();
		String p = System.getProperties().getProperty("file.encoding");

	}

	private static void init() {

		UserContext context = new UserContext(); //создаём контекст пользователя
		User user = new User(context); // создаём пользователя и передаём ему контекст
		Locale l=new Locale("en","US");
		IView view = new ViewConsole(System.out, System.in, ResourceBundle.getBundle("main.resources.locale.message", l)); // создаём представление пользователя
		context.addObserver(view); // добавляем наблюдателя за контекстом
		CommandHistory<CommandHistoryElement> commandHistory = new CommandHistory<CommandHistoryElement>();
		InvokerCommand invokerCommand = new InvokerCommand(commandHistory);
		IDaoFactory daoFactory = new DaoFactory();
		String path = Main.class.getResource("resources/UserContext").getPath();
		File fileUserContext = new File(path);
		IDaoUserContext daoUserContext = new FileDaoUserContext(fileUserContext);
		daoFactory.setDaoUserContext(daoUserContext);

		CommandBuilder commandBuilder = createCommands(new CommandBuilder());

		commandBuilder.setDaoFactory(daoFactory);
		commandBuilder.setReceiver(context);
		view.setCommandBuilder(commandBuilder);
		view.setInvokerCommand(invokerCommand);
		view.setUser(user);
		view.handle();
	}

	private static CommandBuilder createCommands(CommandBuilder commandBuilder) {
		ICommand test = new Test("test");
		ICommand login = new Login("login");
		ICommand logout = new Logout("logout");
		ICommand registration = new Registration("registration");
		ICommand saveUser = new SaveUser("saveuser");
		ICommand exit = new MacroCommand("exit")
				.add(saveUser)
				.add(new Exit("exit"));
		commandBuilder.addCommand(test);
		commandBuilder.addCommand(login);
		commandBuilder.addCommand(logout);
		commandBuilder.addCommand(registration);
		commandBuilder.addCommand(saveUser);
		commandBuilder.addCommand(exit);
		return commandBuilder;
	}

}
