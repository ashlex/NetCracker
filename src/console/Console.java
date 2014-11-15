package console;

import command.ICommand;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {

	public void start() {
		PrintStream ps = System.out;
		ps.println("Добро пожаловать.");
		Scanner scn = new Scanner(System.in);
		String input;
		ICommand com;
		while (true) {
			input = scn.nextLine();
			String[] command = input.split(" ");
			String comName = command[0];
			String[] comAttribute = new String[command.length - 1];
			System.arraycopy(command, 1, comAttribute, 0, comAttribute.length);
			comName = capitalize(comName);

			try {
				com = (ICommand) Class.forName("command." + comName).newInstance();
				com.execute(comAttribute, ps);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	public static String capitalize(String s) {
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
