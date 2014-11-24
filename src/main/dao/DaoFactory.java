package main.dao;

public class DaoFactory implements IDaoFactory {
    private IDaoUserContext daoUserContext;

	@Override
	public void setDaoUserContext(IDaoUserContext daoUserContext){
		if (daoUserContext!=null) {
			this.daoUserContext=daoUserContext;
		}
	}
    @Override
    public IDaoUserContext getDaoUserContext() {
        if(daoUserContext==null){
	        throw new NullPointerException();
        }
	    return daoUserContext;
    }
}
