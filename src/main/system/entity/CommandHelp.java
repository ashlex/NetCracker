package main.system.entity;

public class CommandHelp {
	private String command;
	private String fullHelp;
	private String shortHelp;
	private String syntax;

	public CommandHelp(String command,String fullHelp, String shortHelp , String syntax){
		this.command=command;
		this.fullHelp=fullHelp;
		this.shortHelp=shortHelp;
		this.syntax=syntax;
	}

	public String getFullHelp(){
		String out;
		out = command.toUpperCase()+"%n";
		if(!getSyntax().isEmpty()) {
			out += getSyntax() + "%n" ;
		}
		out+= fullHelp;
		return String.format(out);
	}
	public String getSyntax(){
		return syntax;
	}
	public String getShortHelp(){
		return shortHelp;
	}
	public String getCommand(){
		return this.command;
	}
}
