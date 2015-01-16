package main.dao;

import main.entity.UserContext;

public interface IDaoUserContext {
    /**
     * It's function add new context to data file.
     *
     * @param u Added user context.
     * @return Returns result adding new context to data file, true if success and false if fail.
     */
    public boolean add(UserContext u);
    public UserContext getUser(String login);
    public boolean remove(String login);
    public boolean update(UserContext u);
    public int getCountUsers();
}
