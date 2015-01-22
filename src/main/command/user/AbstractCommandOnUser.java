package main.command.user;

import main.command.ExecuteResult;
import main.command.ICommand;
import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public abstract class AbstractCommandOnUser implements ICommand {
	protected Logger log=Logger.getLogger(this.getClass().getName());
	protected UserContext context;
	protected ArrayList<String> attributes;
	protected IDaoFactory daoFactory;
	protected String alias;


	protected ExecuteResult executeResult;
	protected ResourceBundle resourceBundle;

	public AbstractCommandOnUser(String alias) {
		this.alias=alias;
		this.resourceBundle = ResourceBundle.getBundle("main.resources.locale.message");
		executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "no exec");
	}

	public AbstractCommandOnUser(String alias, ResourceBundle resourceBundle) {
		this.alias=alias;
		this.resourceBundle = resourceBundle;
		executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "no exec");
	}

	public AbstractCommandOnUser(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
		this.alias=alias;
		this.resourceBundle = resourceBundle;
		this.executeResult = executeResult;
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
