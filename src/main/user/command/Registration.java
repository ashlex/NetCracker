package main.user.command;

import main.command.ExecuteResult;
import main.user.entity.UserContext;

import java.io.IOException;
import java.util.ResourceBundle;

public class Registration extends AbstractCommandOnUser {
    private ExecuteResult executeResult;
    private ResourceBundle resourceBundle;

    public Registration() {
        this("registration");
    }

    public Registration(String alias) {
        super(alias);
        this.resourceBundle = ResourceBundle.getBundle("main.resources.locale.message");
        executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "no exec");
    }

    public Registration(String alias, ResourceBundle resourceBundle) {
        super(alias);
        this.resourceBundle = resourceBundle;
        executeResult = new ExecuteResult(this, ExecuteResult.FAIL, "no exec");
    }

    public Registration(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
        super(alias);
        this.resourceBundle = resourceBundle;
        this.executeResult = executeResult;
    }

    @Override
    public ExecuteResult execute() throws IOException {
        if(context.getRole()!=0){
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setMessage("You are already registered.");
        }
        if (attributes != null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.GET_HELP);
                executeResult.setMessage(getHelp());
            } else {
                if(attributes.size()==2) {
                    UserContext userContext = new UserContext();
                    userContext.setNickname(attributes.get(0));
                    userContext.setPassword(attributes.get(1));
                    userContext.setOnline(false);
                    userContext.setRole(2);
                    if(daoFactory.getDaoUserContext().getUser(userContext.getNickname())!=null){
                        executeResult.setResult(ExecuteResult.FAIL);
                        executeResult.setMessage("This nickname exists already.");
                    }else {
                        if (daoFactory.getDaoUserContext().add(userContext)) {
                            executeResult.setResult(ExecuteResult.SUCCESS);
                            executeResult.setMessage("User with nickname " + userContext.getNickname() + " added to users list.");
                        } else {
                            executeResult.setResult(ExecuteResult.FAIL);
                            executeResult.setMessage("RECORDING_FAILED");
                        }
                    }
                }
            }
        }else {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setMessage(resourceBundle.getString("ILLEGAL_ARGUMENT"));
        }
        return executeResult;
    }


}
