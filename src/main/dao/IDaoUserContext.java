package main.dao;

import main.entity.UserContext;

public interface IDaoUserContext {
    public boolean add(UserContext u);
    public UserContext getUser(String login);
    public boolean remove(String login);
    public boolean update(UserContext u);
    public int getCountUsers();
}
