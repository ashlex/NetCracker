package console;

import command.ICommand;
import entity.User;
import entity.UsersBuffer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {
	private final String INVITE="$:";
	private PrintStream ps = System.out;
	private Scanner scn = new Scanner(System.in);
	private String input;
	private ICommand com;
	private String comName;
	private String[] command;
	private String[] comAttribute;
	private User user=null;
	public void start() {
		ps.println("Добро пожаловать.");
		user= UsersBuffer.getInstance().create();
		while (true) {
			ps.println();
			ps.print(user+INVITE);
			input = scn.nextLine();
			command = input.split(" ");
			comName = command[0];
			comAttribute = new String[command.length - 1];
			System.arraycopy(command, 1, comAttribute, 0, comAttribute.length);
			comName = capitalize(comName);
//			for (String s : comAttribute) {
//				ps.println(s);
//			}
			try {
				com = (ICommand) Class.forName("command." + comName).newInstance();
				if(comAttribute.length>0){
					com.setAttributes(comAttribute);
				}
				com.setOutPutStream(ps);
				com.exec();
			} catch (ClassNotFoundException e) {
				ps.println("");
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
