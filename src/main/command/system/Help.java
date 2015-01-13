package main.command.system;

import main.IObserver;
import main.command.ICommand;

import java.io.IOException;
import java.util.*;

/**
 * Created by Alexej on 13.01.2015.
 */
public class Help implements ICommand {
    private Collection<ICommand> commandList;

    public Help(Collection<ICommand> commandList){
        this.commandList=commandList;
    }

    @Override
    public boolean execute() throws IOException {
        for(ICommand command : commandList){
//            command.
        }
        return false;
    }

    @Override
    public String getAlias() {
        return "help";
    }

    @Override
    public String getHelp() {
        return null;
    }

}
