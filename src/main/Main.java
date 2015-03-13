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
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

	private static Logger log;
	public static void main(String[] args) {
		test();
		init();

	}

	private static void test() {


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
		setDaoUserContext(daoFactory);

//		url=Main.class.getResource("resources/CommandHelp");
//		File fileCommandHelp=null;
//		if(url==null) {
//			log.info("Resource \"CommandHelp\" not found.");
//		}else {
//			fileCommandHelp= new File(url.getPath());
//		}


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
				new Exit("quit"),
				new MyInfo("myinfo"),
		};

		for (ICommand command : commands){
			commandBuilder.addCommand(command);
		}

		commandBuilder.addCommand(new MacroCommand("exit", commandBuilder)
						.add(commandBuilder.getCommand("logout"))
						.add(commandBuilder.getCommand("quit"))
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
		log = Logger.getLogger(Main.class.getName());
//		log.severe("test");
//		log.warning("test");
//		log.info("test");
//		log.fine("test");
//		log.finer("test");
//		log.finest("test");
	}

	/**
	 * Adds a concrete DaoUserContext in daoFactory.
	 *
	 * @param daoFactory DaoFactory for adding the concrete DaoUserContext.
	 */
	private static void setDaoUserContext(IDaoFactory daoFactory){
		File fileUserContext=null;
		IDaoUserContext daoUserContext=null;

		URL url=Main.class.getResource("resources/UserContext");
		if(url==null) {
			log.info("Resource \"UserContext\" not found. The work of program may be incorrect.");
		}else {
			fileUserContext = new File(url.getPath());
			if(fileUserContext==null){
				log.severe("Creating a object UserContext failed.");
			}else {
				daoUserContext = new FileDaoUserContext(fileUserContext);
				if(daoUserContext==null){
					log.severe("Creating a object DAOUserContext failed.");
				}else {
					daoFactory.setDaoUserContext(daoUserContext);
				}
			}
		}
	}
}
