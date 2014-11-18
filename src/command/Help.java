package command;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Help implements ICommand{

	OutputStream outputStream=null;
	ArrayList<String> attributes=null;

	@Override
	public void setOutPutStream(OutputStream request) {
		if (request!=null){
			this.outputStream=request;
		}

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if(attributes!=null){
			this.attributes=attributes;
		}
	}

	@Override
	public void setAttributes(String[] attributes) {
		if(attributes.length>0){
			this.attributes=new ArrayList<String>();
			for (String attribute : attributes) {
				this.attributes.add(attribute);
			}
		}
	}

	@Override
	public void exec() throws IOException {
		Map<String,String> map=new HashMap<String, String>();
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
		if(attributes!=null){
			for (String attribute : attributes) {
				outputStream.write((map.get(attribute)+"\r").getBytes());
			}
		}else{
			for (Map.Entry<String, String> entry : map.entrySet()){
					outputStream.write((entry.getValue() + "\r").getBytes());
			}
		}
	}
}
