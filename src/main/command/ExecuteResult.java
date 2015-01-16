package main.command;

/**
 * Created by Alexej on 16.01.2015.
 */
public class ExecuteResult {
    public final static int FAIL =0;
    public final static int GET_HELP =1;
    public final static int SUCCESS =2;
    public final static int WARNING =3;

    private String message;
    private int result;
    private ICommand command;

    public ExecuteResult(ICommand command,int result, String message){
        this.command=command;
        this.result=result;
        this.message=message;
    }
    public ExecuteResult(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
        this.message = "";
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}
