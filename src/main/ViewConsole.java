package main;

import main.command.CommandBuilder;
import main.command.ExecuteResult;
import main.command.ICommand;
import main.command.InvokerCommand;
import main.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ViewConsole implements IView {
	private String invite[] = {"anonymous", "$:"};
	private OutputStream outputStream;
	private InputStream inputStream;
	private User user;
	private InvokerCommand invokerCommand;
	private CommandBuilder commandBuilder;
	private ICommand command;
	private ResourceBundle message;

	public ViewConsole(OutputStream outputStream, InputStream inputStream) {
		this.message= ResourceBundle.getBundle("main.resources.locale.message");
		this.outputStream = outputStream;
		this.inputStream = inputStream;
	}

	@Override
	public void setUser(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	@Override
	public void setInvokerCommand(InvokerCommand invokerCommand) {
		if (invokerCommand != null) {
			this.invokerCommand = invokerCommand;
		}
	}

	@Override
	public void setCommandBuilder(CommandBuilder commandBuilder) {
		if (commandBuilder != null) {
			this.commandBuilder = commandBuilder;
		}
	}

	@Override
	public void handle() {
		Scanner scn = new Scanner(inputStream);
		ExecuteResult result;
		while (true) {
			print("\n"+joinStringArray(invite, " "));
			command = commandBuilder.getCommand(scn.nextLine());
			if(command!=null) {
				result=invokerCommand.storeAndExecute(command, user.getContext());
				print(result.getMessage());
			}else {
				print(message.getString("COMMAND_NO_FOUND"));
			}
		}

	}

	@Override
	public void handleEvent() {
		if (user.getContext().getRole() == 0) {
			invite[0] = "anonymous";
		} else {
			invite[0] = user.toString();
		}
	}

	private void print(String str) {
		try {
			outputStream.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String joinStringArray(String[] array, String separator) {
		String result = "";
		for (String s : array) {
			result += s + separator;
		}
		return result;
	}
}
