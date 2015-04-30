package main.view;

import main.command.CommandBuilder;
import main.command.ICommand;
import main.command.InvokerCommand;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.user.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ViewConsole implements IView {
	private String invite[] = new String[2];
	private OutputStream outputStream;
	private InputStream inputStream;
	private User user;
	private InvokerCommand invokerCommand;
	private CommandBuilder commandBuilder;
	private ICommand command;
	private ResourceBundle message;
	private ResourceBundle general;

	public ViewConsole(OutputStream outputStream, InputStream inputStream) {
		this.message = ResourceBundle.getBundle("main.resources.locale.message");
		this.general = ResourceBundle.getBundle("main.resources.locale.general");
		this.outputStream = outputStream;
		this.inputStream = inputStream;
		invite[0] = general.getString("USER_ANONYMOUS");
		invite[1] = general.getString("USER_WILDCARD_INVITATION");
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
		try {
			ExecuteResult result;
			String inputtedCommand;
			while (true) {
				print("\n" + joinStringArray(invite, " "));
				inputtedCommand = scn.nextLine();
				command = commandBuilder.getCommand(inputtedCommand);
				if (command != null) {
					result = invokerCommand.storeAndExecute(command);
					command = null;
					handlingResult(result);
				} else {
					print(String.format(message.getString("COMMAND_NO_FOUND"), inputtedCommand.split(" ")[0]));
				}			}
		} catch (NoSuchElementException exception) {
			scn.close();
		}

	}

	@Override
	public void handleEvent() {
		invite[0] = user.toString();
	}

	/**
	 * This displays on the console the results of running.
	 *
	 * @param er {@link main.command.entity.ExecuteResult} results of running.
	 */
	private void handlingResult(ExecuteResult er) {
		Response response = er.getResponse();
		if (response != null) {
			Object[] array = response.getResponseArray();
			int type = response.getType();
			switch (type) {
				case Response.STRING:
					print((String) array[0]);
					break;
				case Response.LIST:
					for (Object str1 : array) {
						println((String) str1);
					}
					break;
				case Response.NUMBERED_LIST:
					for (int i = 0; i < array.length; i++) {
						println((i + 1) + ". " + array[i]);
					}
					break;
				default:
			}
		}
	}

	private void print(String str) {
		try {
			outputStream.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void println(String str) {
		try {
			outputStream.write(String.format(str + "%n").getBytes());
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
