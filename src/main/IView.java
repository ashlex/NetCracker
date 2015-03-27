package main;

import main.command.CommandBuilder;
import main.command.InvokerCommand;
import main.user.entity.User;

public interface IView extends IObserver{
    void setUser(User user);
    public void setInvokerCommand(InvokerCommand invokerCommand);
    public void setCommandBuilder(CommandBuilder commandBuilder);
    public void handle();
}
