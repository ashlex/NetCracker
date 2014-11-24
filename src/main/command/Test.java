package main.command;

import java.io.IOException;

public class Test extends CommandBase implements ICommand {

    @Override
    public void execute() throws IOException {
        String str = "root";
        if (attributes != null) {
            str = attributes.get(0);
        }
        context.setNickname(str);
        context.notifyObserver();
    }


}
