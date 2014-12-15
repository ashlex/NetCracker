package main.command.user;

import main.command.ICommand;
import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;

public abstract class AbstractCommandOnUser implements ICommand {
	protected UserContext context;
	protected ArrayList<String> attributes;
	protected IDaoFactory daoFactory;
	protected String alias;

	public AbstractCommandOnUser(String alias){
		this.alias=alias;
	}

	public void setContext(UserContext context) {
		if (context != null) {
			this.context = context;
		}
	}

	public void setAttributes(ArrayList<String> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}
	}
	public void setDaoFactory(IDaoFactory daoFactory) {
		if(daoFactory!=null) {
			this.daoFactory = daoFactory;
		}
	}

	public void reset() {
		context=null;
		attributes=null;
		daoFactory=null;
	}

	public String getAlias() {
		return this.alias;
	}
}
