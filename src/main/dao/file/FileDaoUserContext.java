package main.dao.file;

import main.dao.IDaoUserContext;
import main.dao.file.entity.Row;
import main.user.entity.UserContext;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDaoUserContext implements IDaoUserContext {
	private Parser parser;
	private int rowCount = -1;
	private int operationAfterSynchronisation = 0;
	private final int MAX_OPERATION_BEFORE_SYNCHRONISATION = 1000;
	private static final Logger log = Logger.getLogger(FileDaoCommandHelp.class.getName());

	public FileDaoUserContext(File userContextDF, boolean header) throws IllegalArgumentException {
		if (userContextDF == null) {
			throw new IllegalArgumentException("Object userContextDF is null.");
		}
		parser = new Parser(userContextDF, header);
		rowCount=getRowCount();
	}

	public FileDaoUserContext(File userContextDF) throws IllegalArgumentException {
		if (userContextDF == null) {
			throw new IllegalArgumentException("Object userContextDF is null.");
		}
		parser = new Parser(userContextDF, true);
	}

	@Override
	public boolean add(UserContext u) {
		if (u != null) {
			if (getUser(u.getNickname()) != null) {
				return false;
			}
			//ID;NICKNAME;PASSWORD;NAME;ROLE;ONLINE;
			String[] user = {String.valueOf(getRowCount() + 1), u.getNickname(), u.getPassword(), u.getName(), String.valueOf(u.getRole()), "0"};
			Row<String> row = new Row<String>(user);
			ArrayList<Row> arrayList = parser.getRows();
			if (arrayList.add(row)) {
				if (parser.write(arrayList)) {
					rowCount++;
					synchronisationRowCount();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public UserContext getUser(String nickName) {
		UserContext userContext;
		ArrayList<Row> rows = parser.getRows();
		for (Row<String> row : rows) {
			//ID;NICKNAME;PASSWORD;NAME;ROLE;ONLINE;
			if (row.getRow()[1].equals(nickName)) {
				userContext = new UserContext();
				userContext.setNickname(row.getRow()[1]);
				userContext.setName(row.getRow()[3]);
				try {
					userContext.setRole(Integer.parseInt(row.getRow()[4]));
					userContext.setId(Integer.parseInt(row.getRow()[0]));
				} catch (NumberFormatException e) {
					log.log(Level.SEVERE, "", e);
					return null;
				}
				userContext.setPassword(row.getRow()[2]);
				userContext.setOnline(row.getRow()[5].equals("1") ? true : false);
				return userContext;
			}
		}
		return null;
	}

	@Override
	public boolean remove(String nickName) {
		ArrayList<Row> arrayList = parser.getRows();
		for (Row<String> row : arrayList) {
			if (nickName.equals(row.getRow()[1])) {
				if (arrayList.remove(row)) {
					if (parser.write(arrayList)) {
						rowCount--;
						synchronisationRowCount();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean update(UserContext userContext) {
		ArrayList<Row> rows = parser.getRows();
		boolean res=false;
		for (Row<String> row : rows) {
			//ID;NICKNAME;PASSWORD;NAME;ROLE;ONLINE;
			if (row.getRow()[1].equals(userContext.getNickname())) {
				row.getRow()[0]=String.valueOf(userContext.getId());
				row.getRow()[1]=userContext.getNickname();
				row.getRow()[3]=userContext.getName();
				row.getRow()[4]=String.valueOf(userContext.getRole());
				row.getRow()[2]=userContext.getPassword();
				row.getRow()[5]=userContext.isOnline()?"1":"0";
				res=true;
				break;
			}
		}
		if(res){
			if(parser.write(rows)){
				return true;
			}
		}
		return false;
	}


	private ArrayList<UserContext> getAllUser() {
		UserContext userContext;
		ArrayList<UserContext> userContextsArrayList = new ArrayList<UserContext>();
		ArrayList<Row> rows = parser.getRows();
		for (Row<String> row : rows) {
			//ID;NICKNAME;PASSWORD;NAME;ROLE;ONLINE;
			userContext = new UserContext();
			userContext.setNickname(row.getRow()[1]);
			userContext.setName(row.getRow()[3]);
			try {
				userContext.setRole(Integer.parseInt(row.getRow()[4]));
				userContext.setId(Integer.parseInt(row.getRow()[0]));
			} catch (NumberFormatException e) {
				log.log(Level.SEVERE, "", e);
				continue;
			}
			userContext.setPassword(row.getRow()[2]);
			userContext.setOnline(row.getRow()[5].equals("1") ? true : false);
			userContextsArrayList.add(userContext);
		}
		return userContextsArrayList;
	}

	@Override
	public int getRowCount() {
		if (rowCount >= 0) {
			return rowCount;
		} else {
			rowCount = parser.getRows().size();
			operationAfterSynchronisation = 0;
			return rowCount;
		}
	}

	private boolean synchronisationRowCount() {
		if (operationAfterSynchronisation < MAX_OPERATION_BEFORE_SYNCHRONISATION) {
			return false;
		} else {
			rowCount = parser.getRows().size();
			operationAfterSynchronisation = 0;
			return true;
		}
	}
}
