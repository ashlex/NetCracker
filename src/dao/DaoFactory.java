package dao;

public class DaoFactory {
	private static DaoFactory INSTANCE=null;
//	private final IDaoUser ramDaoUser=new RamDaoUser();
	private final IDaoUser fileDaoUser=new FileDaoUser();
	private DaoFactory(){}
	public static DaoFactory getInstance(){
		if (INSTANCE==null){
			INSTANCE=new DaoFactory();
		}
		return INSTANCE;
	}
//	public IDaoUser getRamDaoUser(){
//		return ramDaoUser;
//	}
	public IDaoUser getFileDaoUser(){
		return fileDaoUser;
	}

}
