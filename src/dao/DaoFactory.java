package dao;

public class DaoFactory {
	private static DaoFactory INSTANCE=null;
	private final IDaoUser ramDaoUser=new RamDaoUser();
	private DaoFactory(){}
	public static DaoFactory getInstance(){
		if (INSTANCE==null){
			INSTANCE=new DaoFactory();
		}
		return INSTANCE;
	}
	public IDaoUser getDaoUser(String daoName){
		if (daoName.equalsIgnoreCase("RamDaoUser")) {
			return ramDaoUser;
		} else {
			return null;
		}
	}

}
