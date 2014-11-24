package main.dao;

import main.entity.UserContext;

import java.io.*;
import java.util.ArrayList;

public class FileDaoUserContext implements IDaoUserContext {
	private enum Cools {NICKNAME, PASSWORD, NAME, ROLE, ONLINE}
	private File uFile;

	//	private String path = FileDaoUser.class.getResource("UserContext").getPath();
	public FileDaoUserContext(File userContextDF) throws IllegalArgumentException {
		if (userContextDF == null) {
			throw new IllegalArgumentException();
		}
		uFile = userContextDF;
	}

	@Override
	public boolean add(UserContext u) {
		if(u==null){
			throw new IllegalArgumentException("UserContext can not be null!");
		}
		if(getUser(u.getNickname())!=null){
			return false;
		}
		try {
			FileWriter fileWriter = new FileWriter(uFile, true);
			String userContext = userContextToString(u) + "\n";
			fileWriter.write(userContext);
			fileWriter.flush();
			fileWriter.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserContext getUser(String nickName) {
		UserContext userContext;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(uFile));
			String line;
			String row[];
			while ((line=bufferedReader.readLine())!=null){
				row=line.split(";");
				if(row[Cools.NICKNAME.ordinal()].equals(nickName)){
					userContext=new UserContext();
					userContext.setNickname(row[Cools.NICKNAME.ordinal()]);
					userContext.setName(row[Cools.NAME.ordinal()]);
					userContext.setRole(Integer.parseInt(row[Cools.ROLE.ordinal()]));
					userContext.setPassword(row[Cools.PASSWORD.ordinal()]);
					userContext.setOnline(row[Cools.ONLINE.ordinal()].equals("1"));
					bufferedReader.close();
					return userContext;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean remove(String nickName) {
		ArrayList<UserContext> userArrayList=getAllUser();
		UserContext userContext=null;
		boolean result=false;
		for (UserContext u:userArrayList){
			if(u.getNickname().equals(nickName)){
				userContext=u;
				break;
			}
		}
		if(userContext!=null) {
			if(userArrayList.remove(userContext)) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(uFile);
					for (UserContext u : userArrayList) {
						fileOutputStream.write((userContextToString(u) + "\n").getBytes());
					}
					fileOutputStream.flush();
					fileOutputStream.close();
					result=true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public boolean update(UserContext userContext) {
		if(remove(userContext.getNickname())) {
			return add(userContext);
		}
		return false;
	}

	@Override
	public int getCountUsers() {
		return getAllUser().size();
	}

	private ArrayList<UserContext> getAllUser() {
		UserContext userContext;
		ArrayList<UserContext> userContextsArrayList = new ArrayList<UserContext>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(uFile)));
			String line;
			String row[];
			while ((line = bufferedReader.readLine()) != null) {
				row=line.split(";");
				userContext=new UserContext();
				userContext.setNickname(row[Cools.NICKNAME.ordinal()]);
				userContext.setName(row[Cools.NAME.ordinal()]);
				userContext.setRole(Integer.parseInt(row[Cools.ROLE.ordinal()]));
				userContext.setPassword(row[Cools.PASSWORD.ordinal()]);
				userContext.setOnline(row[Cools.ONLINE.ordinal()].equals("1"));
				userContextsArrayList.add(userContext);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userContextsArrayList;
	}
	private String userContextToString(UserContext userContext){
		String []str=new String[Cools.values().length];
		str[Cools.NICKNAME.ordinal()]=userContext.getNickname();
		str[Cools.PASSWORD.ordinal()]=userContext.getPassword();
		str[Cools.NAME.ordinal()]=userContext.getName();
		str[Cools.ROLE.ordinal()]=userContext.getRole()+"";
		str[Cools.ONLINE.ordinal()]=userContext.isOnline()?"1":"0";
		String user="";
		for (String s : str) {
			user+=s+";";
		}
		return user;
	}
}
