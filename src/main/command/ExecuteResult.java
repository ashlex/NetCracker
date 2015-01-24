package main.command;

/**
 *
 * Objects on this class are result of execute commands. They contain code of execution,
 * a message received at runtime and a link on execution command.
 */
public class ExecuteResult {
    public final static int FAIL = 0;
    public final static int GET_HELP = 1;
    public final static int SUCCESS = 2;
    public final static int WARNING = 3;
//    public final static int ERROR = 4;

    private String message;
    private int result;
    private ICommand command;

    public ExecuteResult(ICommand command, int result, String message) {
        this.command = command;
        this.result = result;
        this.message = message;
    }

    public ExecuteResult() {
        this.command = null;
        this.result = 0;
        this.message = null;
    }

    public String getMessage() {
        if (message == null) {
            return "";
        }
        return message;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        setResult(result, null);
    }

    public void setResult(int result, String message) {
        if (result>0) {
            this.result = result;
            setMessage(message);
        }
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}
