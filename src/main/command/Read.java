package main.command;

import main.dao.IDaoFactory;
import main.entity.UserContext;

import java.io.IOException;
import java.util.ArrayList;

public class Read implements ICommand {

	@Override
	public void setContext(UserContext context) {

	}

	@Override
	public void setAttributes(ArrayList<String> attributes) {

	}

	@Override
	public void execute() throws IOException {

	}

	@Override
	public void reset() {

	}

    @Override
    public void setDaoFactory(IDaoFactory daoFactory) {

    }
}
