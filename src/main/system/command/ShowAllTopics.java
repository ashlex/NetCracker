package main.system.command;

import main.command.ICommand;
import main.command.IContainsAttributes;
import main.command.IUseDAO;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.dao.IDaoCommandHelp;
import main.dao.IDaoFactory;
import main.dao.IDaoTopics;
import main.system.entity.CommandHelp;
import main.system.entity.Topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ShowAllTopics implements ICommand,IUseDAO,IContainsAttributes{
	private static final Logger log=Logger.getLogger(ShowAllTopics.class.getName());
	private IDaoFactory daoFactory;
	private ArrayList<String> attributes;
	private String alias;
	private ResourceBundle resourceBundle;

	public ShowAllTopics(String alias){
		this.resourceBundle = ResourceBundle.getBundle("main.resources.locale.message");
		if(alias==null){
			log.severe("Attempt to pass null. Alias can't be null. Value of alias set as default.");
			this.alias="showAllTopics";
		}
		this.alias=alias;

	}
	@Override
	public ExecuteResult execute() throws IOException {
		IDaoTopics iDaoTopics=daoFactory.getDaoTopics();
		ArrayList<Topic> topics=iDaoTopics.getAllTopics();
		ArrayList<String> headers=new ArrayList<String>(topics.size());
		for (Topic t:topics){
			headers.add(t.getId()+" "+t.getHeader());
		}
		ExecuteResult executeResult=new ExecuteResult(this,ExecuteResult.SUCCESS,new Response(headers));
		return executeResult;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public String getHelp() {
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
					log.fine(String.format("Help of command \"%1$s\" is empty.", getAlias()));
				}
			}else{
				log.info(String.format(TEMPLATE,"CommandHelp"));
				return String.format(resourceBundle.getString("HELP_NOT_FOUND"), getAlias());
			}
		}else{
			log.info(String.format(TEMPLATE,"DAOCommandHelp"));
		}
		return null;
	}

	@Override
	public void setDaoFactory(IDaoFactory daoFactory) {
		if(daoFactory!=null) {
			this.daoFactory = daoFactory;
		}else{
			log.info("Attempt to pass null. DaoFactory can't be null.");
		}
	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}else{
			log.info("Attempt to pass null. Attributes can't be null.");
		}
	}
}
