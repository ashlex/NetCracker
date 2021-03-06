package main.command.entity;

import main.command.ICommand;

/**
 *
 * Objects on this class are result of execute commands. They contain code of execution,
 * a response received at runtime and a link on execution command.
 */
public class ExecuteResult {
    public final static int FAIL = 0;
    public final static int GET_HELP = 1;
    public final static int SUCCESS = 2;
    public final static int WARNING = 3;
//    public final static int ERROR = 4;

    private Response response;
    private int result;
    private ICommand command;

    public ExecuteResult(ICommand command, int result, Response response) {
        this.command = command;
        this.result = result;
        this.response = response;
    }

    public ExecuteResult() {
        this.command = null;
        this.result = 0;
        this.response = null;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        setResult(result, null);
    }

    public void setResult(int result, Response response) {
        if (result>0) {
            this.result = result;
            setResponse(response);
        }
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}
