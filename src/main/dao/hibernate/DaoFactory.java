package main.dao.hibernate;

import main.dao.IDaoCommandHelp;
import main.dao.IDaoFactory;
import main.dao.IDaoTopics;
import main.dao.IDaoUserContext;

public class DaoFactory implements IDaoFactory {
	@Override
	public void setDaoUserContext(IDaoUserContext daoUserContext) {

	}

	@Override
	public IDaoUserContext getDaoUserContext() {
		return null;
	}

	@Override
	public void setDaoCommandHelp(IDaoCommandHelp daoCommandHelp) {

	}

	@Override
	public IDaoCommandHelp getDaoCommandHelp() {
		return null;
	}

	@Override
	public void setDaoTopics(IDaoTopics daoTopics) {

	}

	@Override
	public IDaoTopics getDaoTopics() {
		return null;
	}
}
