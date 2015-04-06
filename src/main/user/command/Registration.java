package main.user.command;

import main.command.AbstractCommandBase;
import main.command.entity.Attributes;
import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.user.entity.UserContext;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

public class Registration extends AbstractCommandBase {
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
	    Map<String,Attributes.Attribute> allAttribute=this.attributes.getAllAttribute();
        if(context.getRole()!=0){
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setResponse(new Response("You are already registered."));
        }
        if (allAttribute != null) {
            if (isHelp()) {
                return executeResult;
            } else {
                if(allAttribute.size()==2) {
                    UserContext userContext = new UserContext();
                    userContext.setNickname(allAttribute.get("u").getValues()[0]);
                    userContext.setPassword(allAttribute.get("p").getValues()[0]);
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
