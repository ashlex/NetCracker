package main;

import main.command.*;
import main.entity.User;
import main.entity.UserContext;

public class Main {
	public static void main(String []args){

		UserContext context=new UserContext(); //создаём контекст пользователя
		User user=new User(context); // создаём пользователя и передаём ему контекст
		ViewConsole view=new ViewConsole(); // создаём представление пользователя
		context.addObserver(view); // добавляем наблюдателя за контекстом
		InvokerCommand invokerCommand=new InvokerCommand();

		CommandFactory commandFactory=new CommandFactory();
		commandFactory.setReceiver(context);
		ICommand test=new Test();
		ICommand exit=new Exit();
		commandFactory.addCommand("test",test);
		commandFactory.addCommand("exit",exit);
		view.setCommandFactory(commandFactory);
		view.setInputStream(System.in);
		view.setInvokerCommand(invokerCommand);
		view.setOutputStream(System.out);
		view.setUser(user);
		view.handle();
	}

}
