package main.command;

public interface IManagementCommand {
	public void setInvoker(IInvoker invoker);
	public void setCommandBuilder(CommandBuilder commandBuilder);
}
