package main.command.user;

import main.command.ExecuteResult;
import main.entity.UserContext;

import java.io.IOException;
import java.util.ResourceBundle;

public class Login extends AbstractCommandOnUser {
    private ExecuteResult executeResult;
    private ResourceBundle resourceBundle;

    public Login() {
        this("login");
    }

    public Login(String alias) {
        super(alias);
        this.resourceBundle = ResourceBundle.getBundle("main.resources.locale.message");
        executeResult = new ExecuteResult(this, ExecuteResult.NO_EXEC, "no exec");
    }

    public Login(String alias, ResourceBundle resourceBundle) {
        super(alias);
        this.resourceBundle = resourceBundle;
        executeResult = new ExecuteResult(this, ExecuteResult.NO_EXEC, "no exec");
    }

    public Login(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
        super(alias);
        this.resourceBundle = resourceBundle;
        this.executeResult = executeResult;
    }

    @Override
    public ExecuteResult execute() throws IOException {
        if (attributes != null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.EXEC_HELP);
                executeResult.setMessage(getHelp());
            } else {
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
                        }
                    }
                }
            }
        } else {
            executeResult.setMessage(resourceBundle.getString("ILLEGAL_ARGUMENT"));
        }
        return executeResult;
    }

    @Override
    public String getHelp() {
        return "[-login] [-password] | [-help|-?]";
    }

}
