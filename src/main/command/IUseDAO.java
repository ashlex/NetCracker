package main.command;

import main.dao.IDaoFactory;

public interface IUseDAO {
	public void setDaoFactory(IDaoFactory daoFactory);
}
