package main;

import main.command.CommandFactory;
import main.command.ICommand;
import main.command.InvokerCommand;
import main.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ViewConsole implements IObserver {
	private String invite[]={"anonymous","$:"};
	private OutputStream outputStream;
	private InputStream inputStream;
	private User user;
	private InvokerCommand invokerCommand;
	private CommandFactory commandFactory;
	private ICommand command;

	public void setUser(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	public void setInvokerCommand(InvokerCommand invokerCommand) {
		if (invokerCommand != null) {
			this.invokerCommand = invokerCommand;
		}
	}

	public void setCommandFactory(CommandFactory commandFactory) {
		if (commandFactory != null) {
			this.commandFactory = commandFactory;
		}
	}

	public void setOutputStream(OutputStream outputStream) {
		if (outputStream != null) {
			this.outputStream = outputStream;
		}
	}

	public void setInputStream(InputStream inputStream) {
		if (inputStream != null) {
			this.inputStream = inputStream;
		}
	}

	public void handle() {
		Scanner scn=new Scanner(inputStream);
		while (true){
			print(joinString(invite, " "));
			command=commandFactory.getCommand(scn.nextLine());
			invokerCommand.storeAndExecute(command);
		}

	}
	public void print(String str){
		try {
			outputStream.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleEvent() {
		invite[0]=user.toString();
	}

	private String joinString(String[] array, String separator){
		String result="";
		for (String s:array){
			result+=s+separator;
		}
		return result;
	}
}
