package main.system.command;

import main.command.AbstractCommandBase;
import main.command.entity.ExecuteResult;

import java.io.IOException;

public class ModifyTopic extends AbstractCommandBase {

	public ModifyTopic(String alias) {
		super(alias);
	}

	@Override
	public ExecuteResult execute() throws IOException {
		return null;
	}
}
