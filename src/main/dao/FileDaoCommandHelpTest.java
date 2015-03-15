package main.dao;

import main.command.system.Exit;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDaoCommandHelpTest {
	File f;
	IDaoCommandHelp fileDaoCommandHelp;

	@Before
	public void setUp() throws Exception {
		try {
			f= File.createTempFile("tmp", ".txt");
			FileOutputStream fileOutputStream=new FileOutputStream(f);
			String str="exit;[-p]{-y};this is test commandhelp;\n" +
					"login;[-p]{-y};this is test2 commandhelp2;\n" +
					"registratoin;[-p]{-y};this is test3 commandhelp3;\n";
			fileOutputStream.write(str.getBytes());
			fileOutputStream.close();
			fileDaoCommandHelp = new FileDaoCommandHelp(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetHelp() throws Exception {
		System.out.println(fileDaoCommandHelp.getHelp(new Exit("exit")).getFullHelp());
		System.out.println(fileDaoCommandHelp.getHelp(new Exit("exit")).getSyntax());
		System.out.println(fileDaoCommandHelp.getHelp(new Exit("login")).getFullHelp());
		System.out.println(fileDaoCommandHelp.getHelp(new Exit("login")).getSyntax());
	}

}