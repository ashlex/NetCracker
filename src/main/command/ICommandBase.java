package main.command;

import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;

public interface ICommandBase {
	public void setContext(UserContext context);
	public void setAttributes(ArrayList<String> attributes);
	void setDaoFactory(IDaoFactory daoFactory);
	public void reset();
}
