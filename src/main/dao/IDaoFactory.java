package main.dao;

public interface IDaoFactory {
	public void setDaoUserContext(IDaoUserContext daoUserContext);
    public IDaoUserContext getDaoUserContext();
}
