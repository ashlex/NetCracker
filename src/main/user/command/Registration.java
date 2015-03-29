package main.user.command;

import main.command.entity.ExecuteResult;
import main.command.entity.Response;
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
        executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
    }

    public Registration(String alias, ResourceBundle resourceBundle) {
        super(alias);
        this.resourceBundle = resourceBundle;
        executeResult = new ExecuteResult(this, ExecuteResult.FAIL, null);
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
            executeResult.setResponse(new Response("You are already registered."));
        }
        if (attributes != null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.GET_HELP);
                executeResult.setResponse(new Response(getHelp()));
            } else {
                if(attributes.size()==2) {
                    UserContext userContext = new UserContext();
                    userContext.setNickname(attributes.get(0));
                    userContext.setPassword(attributes.get(1));
                    userContext.setOnline(false);
                    userContext.setRole(2);
                    if(daoFactory.getDaoUserContext().getUser(userContext.getNickname())!=null){
                        executeResult.setResult(ExecuteResult.FAIL);
                        executeResult.setResponse(new Response("This nickname exists already."));
                    }else {
                        if (daoFactory.getDaoUserContext().add(userContext)) {
                            executeResult.setResult(ExecuteResult.SUCCESS);
                            executeResult.setResponse(new Response("User with nickname " + userContext.getNickname() + " added to users list."));
                        } else {
                            executeResult.setResult(ExecuteResult.FAIL);
                            executeResult.setResponse(new Response("RECORDING_FAILED"));
                        }
                    }
                }
            }
        }else {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setResponse(new Response(resourceBundle.getString("ILLEGAL_ARGUMENT")));
        }
        return executeResult;
    }


}
