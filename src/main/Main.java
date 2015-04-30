package main;

import main.command.CommandBuilder;
import main.command.ICommand;
import main.command.InvokerCommand;
import main.command.MacroCommand;
import main.command.entity.CommandHistory;
import main.command.entity.CommandHistoryElement;
import main.dao.*;
import main.dao.file.FileDaoCommandHelp;
import main.dao.file.FileDaoTopics;
import main.dao.file.FileDaoUserContext;
import main.system.command.Exit;
import main.system.command.Help;
import main.system.command.Topics;
import main.user.command.*;
import main.user.entity.User;
import main.user.entity.UserContext;
import main.view.IView;
import main.view.ViewConsole;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

	private static Logger log;
	private static boolean debug = false;

	public static void main(String[] args) {
		configureLog();
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		org.hibernate.Session session=  sessionFactory.openSession();
		main.dao.hibernate.entity.User u =(main.dao.hibernate.entity.User)session.load(main.dao.hibernate.entity.User.class,1);
		main.dao.hibernate.entity.User u2=new main.dao.hibernate.entity.User();
		System.out.println(u);
		u2.setNickName("test2");
		u2.setPassword("test");
		System.out.println(u2);
		session.getTransaction().begin();
		session.save(u2);
		session.getTransaction().commit();
		u =(main.dao.hibernate.entity.User)session.load(main.dao.hibernate.entity.User.class,2);
		System.out.println(u);
		session.close();
		System.exit(0);

		if (args.length > 0) {
			for (String arg : args) {
				if ("-debug".equalsIgnoreCase(arg)) {
					debug = true;
				}
			}
		}
		init();
	}

	private static void init() {
		UserContext context = new UserContext();
		User user = new User(context);
		Locale l = new Locale("en", "US");
		IView view = new ViewConsole(System.out, System.in); // This create new view.
		context.addObserver(view); // Here we add observer for context.
		CommandHistory<CommandHistoryElement> commandHistory = new CommandHistory<CommandHistoryElement>();
		InvokerCommand invokerCommand = new InvokerCommand(commandHistory);

		Dao dao=new Dao();
		IDaoFactory daoFactory = dao.getFactory(Dao.FILE);
		setDao(daoFactory);

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
		ICommand commands[] = {
				new Login("login"),
				new Logout("logout"),
				new Registration("registration"),
				new SaveUser("saveuser"),
				new Exit("quit"),
				new MyInfo("myinfo"),
				new Help("help", commandBuilder),
				new Topics("topic")
		};

		for (ICommand command : commands) {
			commandBuilder.addCommand(command);
		}

		commandBuilder.addCommand(new MacroCommand("exit", commandBuilder)
						.add(commandBuilder.getCommand("logout"))
						.add(commandBuilder.getCommand("quit"))
		);

		return commandBuilder;
	}

	private static void configureLog() {
		InputStream logConfigInputStream;
		if (debug) {
			logConfigInputStream = Main.class.getResourceAsStream("resources/log_debug.properties");
		} else {
			logConfigInputStream = Main.class.getResourceAsStream("resources/log.properties");
		}
		try {
			LogManager.getLogManager().readConfiguration(logConfigInputStream);
		} catch (IOException e) {
			System.out.println(e);
		}
		log = Logger.getLogger(Main.class.getName());
//		for (int i = 0; i < 10000; i++) {
//			log.severe("test");
//			log.warning("test");
//			log.info("test");
//			log.fine("test");
//			log.finer("test");
//			log.finest("test");
//		}
	}

	/**
	 * Adds a concrete DaoUserContext in daoFactory.
	 *
	 * @param daoFactory DaoFactory for adding the concrete DaoUserContext.
	 */
	private static void setDao(IDaoFactory daoFactory) {
		IDaoUserContext daoUserContext = null;
		IDaoCommandHelp daoCommandHelp = null;
		IDaoTopics daoTopics = null;
		if(daoFactory instanceof main.dao.file.DaoFactory) {
			File file = null;
			String[] resources = {
					"UserContext",
					"CommandHelp",
					"Topics",
			};
			URL url = null;
			for (String resName : resources) {
				url = Main.class.getResource("resources/data/" + resName);
				if (url == null) {
					log.info("Resource \"" + resName + "\" not found. The work of program may be incorrect.");
				} else {
					file = new File(url.getPath());
					if (file == null) {
						log.severe("Creating a object " + resName + " failed.");
					} else {
						if ("UserContext".equals(resName)) {
							daoUserContext = new FileDaoUserContext(file);
							if (daoUserContext == null) {
								log.severe("Creating a object DAO" + resName + " failed.");
							} else {
								daoFactory.setDaoUserContext(daoUserContext);
							}
						} else if ("CommandHelp".equals(resName)) {

							daoCommandHelp = new FileDaoCommandHelp(file);
							if (daoCommandHelp == null) {
								log.severe("Creating a object DAO" + resName + " failed.");
							} else {
								daoFactory.setDaoCommandHelp(daoCommandHelp);
							}
						} else if ("Topics".equals(resName)) {
							daoTopics = new FileDaoTopics(file);
							if (daoTopics == null) {
								log.severe("Creating a object DAO" + resName + " failed.");
							} else {
								daoFactory.setDaoTopics(daoTopics);
							}
						}
					}
				}
			}
		}else if(daoFactory instanceof main.dao.hibernate.DaoFactory){

		}
	}
}
