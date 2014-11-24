package main.command;

import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public interface ICommand extends ICommandBase{

    public void execute() throws IOException;

}
