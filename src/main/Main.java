package main;

import main.command.*;
import main.dao.DaoFactory;
import main.dao.FileDaoUserContext;
import main.dao.IDaoFactory;
import main.dao.IDaoUserContext;
import main.entity.User;
import main.entity.UserContext;

import java.io.File;

public class Main {
	public static void main(String []args){
//		System.exit(0);
		UserContext context=new UserContext(); //создаём контекст пользователя
		User user=new User(context); // создаём пользователя и передаём ему контекст
		IView view=new ViewConsole(); // создаём представление пользователя
		context.addObserver(view); // добавляем наблюдателя за контекстом
		InvokerCommand invokerCommand=new InvokerCommand();
		IDaoFactory daoFactory=new DaoFactory();
		String path = Main.class.getResource("resources/UserContext").getPath();
		File fileUserContext=new File(path);
		IDaoUserContext daoUserContext=new FileDaoUserContext(fileUserContext);
		daoFactory.setDaoUserContext(daoUserContext);

		CommandBuilder commandBuilder =new CommandBuilder();
		commandBuilder.setDaoFactory(daoFactory);
		commandBuilder.setReceiver(context);
		ICommand test=new Test();
		ICommand login=new Login();
		ICommand logout=new Logout();
		ICommand registration=new Registration();
		ICommand exit=new Exit();
		commandBuilder.addCommand("test",test);
		commandBuilder.addCommand("login",login);
		commandBuilder.addCommand("logout",logout);
		commandBuilder.addCommand("registration",registration);
		commandBuilder.addCommand("reg",registration);
		commandBuilder.addCommand("exit",exit);
		view.setCommandBuilder(commandBuilder);
		view.setInvokerCommand(invokerCommand);
		view.setUser(user);
		view.handle();
	}

}
