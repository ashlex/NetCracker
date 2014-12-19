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
import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		test();
	}

	private static void test() {

		System.out.println("RU2-011".matches("([a-zA-Z]{2,8})[_-]([a-zA-Z]{2}|[0-9]{3})"));

	}

	private static void init() {

		UserContext context = new UserContext(); //создаём контекст пользователя
		User user = new User(context); // создаём пользователя и передаём ему контекст
		Locale l=new Locale("en","US");
		IView view = new ViewConsole(System.out, System.in); // создаём представление пользователя
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
						.add(commandBuilder.getCommand("saveuser"))
						.add(new Exit("exit"))
		);

		return commandBuilder;
	}

}
