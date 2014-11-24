package main;

import main.command.CommandBuilder;
import main.command.ICommand;
import main.command.InvokerCommand;
import main.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ViewConsole implements IView {
    private String invite[] = {"anonymous", "$:"};
    private OutputStream outputStream;
    private InputStream inputStream;
    private User user;
    private InvokerCommand invokerCommand;
    private CommandBuilder commandBuilder;
    private ICommand command;

    public ViewConsole(){
        this.outputStream=System.out;
        this.inputStream=System.in;
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

//    @Override
//    public void setOutputStream(OutputStream outputStream) {
//        if (outputStream != null) {
//            this.outputStream = outputStream;
//        }
//    }
//
//    @Override
//    public void setInputStream(InputStream inputStream) {
//        if (inputStream != null) {
//            this.inputStream = inputStream;
//        }
//    }

    @Override
    public void handle() {
        Scanner scn = new Scanner(inputStream);
        while (true) {
            print(joinString(invite, " "));
            command = commandBuilder.getCommand(scn.nextLine());
            invokerCommand.storeAndExecute(command);
        }

    }

    private void print(String str) {
        try {
            outputStream.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleEvent() {
	    if(user.getContext().getRole()==0){

		    invite[0] = "anonymous";
	    }else {
		    invite[0] = user.toString();
	    }
    }

    private String joinString(String[] array, String separator) {
        String result = "";
        for (String s : array) {
            result += s + separator;
        }
        return result;
    }
}
