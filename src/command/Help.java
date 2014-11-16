package command;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Help implements ICommand{

	@Override
	public void execute(String[] args, OutputStream request) {
		Map<String,String> map=new HashMap<String, String>();
//		try {
		Scanner scn= null;
		try {
			scn = new Scanner(new File(Help.class.getResource("Help").getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scn.hasNextLine()){
				String str=scn.nextLine();
				String out[]=str.split(";");
				String comName=str.split(" ")[0];
				str="";
				for (String s:out){
					str+=s+"\n\t";
				}
				map.put(comName,str);
			}
//		}
		if(args.length>0){
			((PrintStream)request).println(map.get(args[0]));
		}else{
			for (Map.Entry<String, String> entry : map.entrySet()){
				((PrintStream)request).println(entry.getValue());
			}
		}

	}
}
