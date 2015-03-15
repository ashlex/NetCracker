package main.dao;

import java.util.logging.Logger;

public class DaoFactory implements IDaoFactory {
	private IDaoUserContext daoUserContext;
	private IDaoCommandHelp daoCommandHelp;
	private static final Logger log=Logger.getLogger(DaoFactory.class.getName());

	public DaoFactory() {
		this.daoUserContext = null;
		this.daoCommandHelp = null;
	}

	@Override
	public void setDaoUserContext(IDaoUserContext daoUserContext) {
		if (daoUserContext != null) {
			this.daoUserContext = daoUserContext;
		}else {
			log.fine("Trying make DAOUserContest to null.");
		}
	}

	@Override
	public IDaoUserContext getDaoUserContext() {
		return daoUserContext;
	}

	@Override
	public void setDaoCommandHelp(IDaoCommandHelp daoCommandHelp) {
		if (daoCommandHelp != null) {
			this.daoCommandHelp = daoCommandHelp;
		}else{
			log.fine("Trying make DAOCommandHelp to null.");
		}
	}

	@Override
	public IDaoCommandHelp getDaoCommandHelp() {
		return this.daoCommandHelp;
	}
}
