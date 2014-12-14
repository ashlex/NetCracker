package main.entity;

import main.command.ICommand;
import main.command.IPerformer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CommandHistoryElement {
	private IPerformer performer;
	private ICommand command;
	private Date dateTimeWrite;
	private boolean result;

	public CommandHistoryElement(IPerformer performer, ICommand command, boolean result){
		this.performer=performer;
		this.command=command;
		this.dateTimeWrite= new Date();
		this.result=result;
	}

	public String toString(){
		String str;
		SimpleDateFormat dateFormat=new SimpleDateFormat(ResourceBundle.getBundle("main.resources.locale.general").getString("DATE_FORMAT"));
		str=dateFormat.format(dateTimeWrite)+":"+performer.getNickname()+" "+command.getClass().getName()+" "+result;
		return str;
	}

}
