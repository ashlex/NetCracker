package main.dao.hibernate.entity;

public class Command {
	private int id;
	private int gId;
	private String shortHelp;
	private String fullHelp;
	private String syntax;

	public Command(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getShortHelp() {
		return shortHelp;
	}

	public void setShortHelp(String shortHelp) {
		this.shortHelp = shortHelp;
	}

	public String getFullHelp() {
		return fullHelp;
	}

	public void setFullHelp(String fullHelp) {
		this.fullHelp = fullHelp;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
}
