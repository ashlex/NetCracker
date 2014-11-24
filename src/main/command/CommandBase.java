package main.command;

import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;

public class CommandBase implements ICommandBase {
	protected UserContext context;
	protected ArrayList<String> attributes;
	protected IDaoFactory daoFactory;

	@Override
	public void setContext(UserContext context) {
		if (context != null) {
			this.context = context;
		}
	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}
	}
	@Override
	public void setDaoFactory(IDaoFactory daoFactory) {
		if(daoFactory!=null) {
			this.daoFactory = daoFactory;
		}
	}


	@Override
	public void reset() {
		context=null;
		attributes=null;
		daoFactory=null;
	}
}
