package main.user.command;

import main.command.IContainsAttributes;
import main.command.IUseDAO;
import main.command.entity.ExecuteResult;
import main.command.ICommand;
import main.dao.IDaoCommandHelp;
import main.dao.IDaoFactory;
import main.system.entity.CommandHelp;
import main.user.entity.UserContext;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public abstract class AbstractCommandOnUser implements ICommand,IUseDAO,IContainsAttributes {
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
		executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
	}

	public AbstractCommandOnUser(String alias, ResourceBundle resourceBundle) {
		this.alias=alias;
		this.resourceBundle = resourceBundle;
		executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
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

	public void reset() {
		context=null;
		attributes=null;
		daoFactory=null;
	}

	public String getAlias() {
		return this.alias;
	}

	public String getHelp(){
		final String TEMPLATE="Trying get \"%1$s\" is failed. Object \"%1$s\" not set.";
		IDaoCommandHelp daoCommandHelp=daoFactory.getDaoCommandHelp();
		if(daoCommandHelp!=null){
			CommandHelp commandHelp=daoCommandHelp.getHelp(this);
			if(commandHelp!=null){
				String help="";
				if (!(help=commandHelp.getSyntax()).isEmpty()){
					String shortHelp=commandHelp.getShortHelp();
					if(!shortHelp.isEmpty()) {
						return String.format(help + "%n   " + shortHelp +"%n   "+String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
					}else{
						return String.format(help +"%n   "+String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
					}
				}else if(!(help=commandHelp.getShortHelp()).isEmpty()){
					return String.format(help +"%n   "+String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
				}else if(!(help=commandHelp.getFullHelp()).isEmpty()){
					return help;
				}else{
					log.fine(String.format("Help of command \"%1$s\" is empty.", this.getAlias()));
				}
			}else{
				log.info(String.format(TEMPLATE,"CommandHelp"));
				return String.format(resourceBundle.getString("HELP_NOT_FOUND"), this.getAlias());
			}
		}else{
			log.info(String.format(TEMPLATE,"DAOCommandHelp"));
		}
		return null;
	}
}
