package main.dao;


public class Dao {
	public final static int FILE = 1;
	public final static int HIBERNATE = 2;
	private main.dao.file.DaoFactory fileFactory;
	private main.dao.hibernate.DaoFactory hibernateFactory;

	public Dao() {
		fileFactory = null;
		hibernateFactory = null;
	}

	/**
	 * This create and return a DaoFactory.
	 *
	 * @param key It's key to DaoFactory. It may be 1 for DaoFactory of file and 2 for DaoFactory of hibernate.
	 * @return If key is valid, it return a {@code IDaoFactory} or {@code NULL} if key is no valid.
	 */
	public IDaoFactory getFactory(int key) {
		switch (key) {
			case FILE:
				if (fileFactory != null) {
					return fileFactory;
				} else {
					fileFactory = new main.dao.file.DaoFactory();
					return fileFactory;
				}
			case HIBERNATE:
				if (hibernateFactory != null) {
					return hibernateFactory;
				} else {
					hibernateFactory = new main.dao.hibernate.DaoFactory();
					return hibernateFactory;
				}
			default: return null;
		}
	}
}
