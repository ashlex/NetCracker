package main.user.command;

import main.command.entity.ExecuteResult;
import main.command.entity.Response;

import java.io.IOException;
import java.util.ResourceBundle;

public class SaveUser extends AbstractCommandOnUser {

    public SaveUser() {
        super("saveuser");
    }

    public SaveUser(String alias) {
        super(alias);
    }

    public SaveUser(String alias, ResourceBundle resourceBundle) {
        super(alias, resourceBundle);
    }

    public SaveUser(String alias, ResourceBundle resourceBundle, ExecuteResult executeResult) {
        super(alias, resourceBundle, executeResult);
    }

    @Override
    public ExecuteResult execute() throws IOException {
        if (attributes!=null) {
            if (attributes.contains("help") || attributes.contains("?")) {
                executeResult.setResult(ExecuteResult.GET_HELP);
                executeResult.setResponse(new Response(getHelp()));
                return executeResult;
            }
        }

        if (!context.isOnline()) {
            executeResult.setResult(ExecuteResult.FAIL);
            executeResult.setResponse(new Response(resourceBundle.getString("NO_AUTHORISATION")));
            log.info("Attempt to save is failed, because you don't logged in.");
        } else {
            if (daoFactory.getDaoUserContext().update(context)) {
                executeResult.setResult(ExecuteResult.SUCCESS, new Response(resourceBundle.getString("CHANGES_SAVED")));
                log.info(resourceBundle.getString("CHANGES_SAVED"));
            } else {
                executeResult.setResult(ExecuteResult.FAIL, new Response(resourceBundle.getString("ERROR_IN_SAVING")));
                log.severe("User " + context.getNickname() + ". " + resourceBundle.getString("ERROR_IN_SAVING"));
            }
        }
        return executeResult;
    }


}
