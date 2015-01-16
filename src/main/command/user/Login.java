package main.command.user;

import main.command.ExecuteResult;
import main.entity.UserContext;

import java.io.IOException;
import java.util.ResourceBundle;

public class Login extends AbstractCommandOnUser {

    public Login(){
        super("login");
    }
    public Login(String alias) {
        super(alias);
    }

    public Login(String alias, ResourceBundle resourceBundle) {
        super(alias, resourceBundle);
    }

    public Login(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
        super(alias, resourceBundle, executeResult);
    }

    @Override
    public ExecuteResult execute() throws IOException {
        if (context.isOnline()) {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setMessage("Can not log in because it is already authorized.");
            return executeResult;
        }
        if (attributes != null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.GET_HELP);
                executeResult.setMessage(getHelp());
                return executeResult;
            }

            if (attributes.size() >= 2) {
                UserContext userContext = daoFactory.getDaoUserContext().getUser(attributes.get(0));
                if (userContext != null) {
                    if (userContext.getPassword().equals(attributes.get(1))) {
                        context.setNickname(userContext.getNickname());
                        context.setName(userContext.getName());
                        context.setRole(userContext.getRole());
                        context.setPassword(userContext.getPassword());
                        context.setOnline(true);
                        daoFactory.getDaoUserContext().update(context);
                        context.notifyObserver();
                        executeResult.setResult(ExecuteResult.SUCCESS);
                        return executeResult;
                    }
                }else{
                    executeResult.setResult(ExecuteResult.FAIL);
                    executeResult.setMessage(resourceBundle.getString("NICKNAME_NOT_FOUND"));
                    return executeResult;
                }
            }else{
                executeResult.setResult(ExecuteResult.FAIL);
                executeResult.setMessage(resourceBundle.getString("ILLEGAL_ARGUMENT"));
            }

        } else {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setMessage(resourceBundle.getString("ILLEGAL_ARGUMENT"));
        }
        return executeResult;
    }

    @Override
    public String getHelp() {
        return "[-login] [-password] | [-help|-?]";
    }

}
