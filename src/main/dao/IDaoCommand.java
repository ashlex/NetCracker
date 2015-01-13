package main.dao;

/**
 * Created by Alexej on 13.01.2015.
 *
 * This interface gives functionality for get information on commands.
 */
public interface IDaoCommand {
    public String getSmallHelp();
    public String getFullHelp();
}
