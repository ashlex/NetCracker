package main.command;

import main.command.user.AbstractCommandOnUser;

import java.io.IOException;

public class Test extends AbstractCommandOnUser {

	public Test(String alias) {
		super(alias);
	}

	@Override
    public ExecuteResult execute() throws IOException {
        String str = "root";
        if (attributes != null) {
            str = attributes.get(0);
        }
        context.setNickname(str);
        context.notifyObserver();
	    return null;
    }

    @Override
    public String getHelp() {
        return null;
    }


}
