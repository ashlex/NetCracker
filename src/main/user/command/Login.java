package main.user.command;

import main.command.entity.ExecuteResult;
import main.command.entity.Response;
import main.user.entity.UserContext;

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
            executeResult.setResponse(new Response("You cannot login, because already authorized to."));
            return executeResult;
        }
        if (attributes != null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.GET_HELP);
                executeResult.setResponse(new Response(getHelp()));
	            log.finer("Calling getHelp.");
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
                        executeResult.setResult(ExecuteResult.SUCCESS, new Response(String.format(resourceBundle.getString("WELCOME"),context.getName())));
                        log.fine("User "+context.getNickname()+" login.");
                        log.fine(context.toString());
                        return executeResult;
                    }
                }else{
                    executeResult.setResult(ExecuteResult.FAIL);
                    executeResult.setResponse(new Response(resourceBundle.getString("NICKNAME_NOT_FOUND")));
	                log.fine("User with this nickname is not found.");
                    return executeResult;
                }
            }else{
                executeResult.setResult(ExecuteResult.FAIL);
                executeResult.setResponse(new Response(resourceBundle.getString("ILLEGAL_ARGUMENT")));
	            log.fine("Count of attributes is less than two.");
            }

        } else {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setResponse(new Response(resourceBundle.getString("ILLEGAL_ARGUMENT")));
	        log.info("Collection of attributes is null.");
        }
        return executeResult;
    }

//    @Override
//    public String getHelp() {
//        return "[-login] [-password] | [-help|-?]";
//    }

}
