package main.command;

import main.command.entity.Attributes;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.dao.IDaoCommandHelp;
import main.dao.IDaoFactory;
import main.system.entity.CommandHelp;
import main.user.entity.UserContext;

import java.util.ResourceBundle;
import java.util.logging.Logger;

public abstract class AbstractCommandBase implements IContainsAttributes, IUseDAO, ICommand {
	protected Logger log = Logger.getLogger(this.getClass().getName());
	protected UserContext context;
	protected Attributes attributes;
	protected String alias;
	protected IDaoFactory daoFactory;


	protected ExecuteResult executeResult;
	protected ResourceBundle resourceBundle;

	public AbstractCommandBase(String alias) {
		if (alias == null) {
			log.severe("Attempt to pass null. Alias can't be null. Value of alias set as default.");
		} else {
			this.alias = alias;
		}
		this.resourceBundle = ResourceBundle.getBundle("main.resources.locale.message");
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
	}

	public AbstractCommandBase(String alias, ResourceBundle resourceBundle) {
		if (alias == null) {
			log.severe("Attempt to pass null. Alias can't be null. Value of alias set as default.");
		} else {
			this.alias = alias;
		}
		this.resourceBundle = resourceBundle;
		this.executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
	}

	public AbstractCommandBase(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
		if(alias==null){
			log.severe("Attempt to pass null. Alias can't be null. Value of alias set as default.");
		}else{
			this.alias=alias;
		}
		this.resourceBundle = resourceBundle;
		this.executeResult = executeResult;
	}

	public void setContext(UserContext context) {
		if (context != null) {
			this.context = context;
		}
	}

	@Override
	public void setAttributes(Attributes attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}else{
			log.info("Attempt to pass null. Attributes can't be null.");
		}
	}

	@Override
	public void setDaoFactory(IDaoFactory daoFactory) {
		if (daoFactory != null) {
			this.daoFactory = daoFactory;
		}
	}


	public void reset() {
		context = null;
		attributes = null;
		daoFactory = null;
	}

	public String getAlias() {
		return this.alias;
	}

	public String getHelp() {
		final String TEMPLATE = "Trying get \"%1$s\" is failed. Object \"%1$s\" not set.";
		IDaoCommandHelp daoCommandHelp = daoFactory.getDaoCommandHelp();
		if (daoCommandHelp != null) {
			CommandHelp commandHelp = daoCommandHelp.getHelp(this);
			if (commandHelp != null) {
				String help = "";
				if (!(help = commandHelp.getSyntax()).isEmpty()) {
					String shortHelp = commandHelp.getShortHelp();
					if (!shortHelp.isEmpty()) {
						return String.format(help + "%n   " + shortHelp + "%n   " + String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
					} else {
						return String.format(help + "%n   " + String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
					}
				} else if (!(help = commandHelp.getShortHelp()).isEmpty()) {
					return String.format(help + "%n   " + String.format(resourceBundle.getString("FOR_GET_MORE_INFORMATION"), getAlias()));
				} else if (!(help = commandHelp.getFullHelp()).isEmpty()) {
					return help;
				} else {
					log.fine(String.format("Help of command \"%1$s\" is empty.", this.getAlias()));
				}
			} else {
				log.info(String.format(TEMPLATE, "CommandHelp"));
				return String.format(resourceBundle.getString("HELP_NOT_FOUND"), this.getAlias());
			}
		} else {
			log.info(String.format(TEMPLATE, "DAOCommandHelp"));
		}
		return null;
	}

	protected boolean isHelp() {
		if (attributes.getAllAttribute() != null && (attributes.getAllAttribute().containsKey("help") || attributes.getAllAttribute().containsKey("?"))) {
			this.executeResult.setResult(ExecuteResult.GET_HELP);
			this.executeResult.setResponse(new Response(getHelp()));
			log.fine("Calling getHelp.");
			return true;
		}
		return false;
	}
}
