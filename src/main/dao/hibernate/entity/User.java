package main.dao.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	int userId;
	String nickName;
	String password;
	String locale;
	String surName;
	String firstName;
	Set groupId = new HashSet<Group>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Set getGroupId() {
		return groupId;
	}

	public void setGroupId(Set groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "userId=" +userId+
				"\nnickName=" +nickName+
				"\npassword=" +password+
				"\nlocale=" +locale+
				"\nsurName=" +surName+
				"\nfirstName="+firstName+
				"\ngroups="+groupId;
	}
}
