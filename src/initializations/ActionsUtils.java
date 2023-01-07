package initializations;

import java.util.HashMap;
import browsingoperations.ActionExec;

public final class ActionsUtils {

    private ActionsUtils() { }

    /**
     * Method to initialise the Login Action
     * @return
     */
    public static ActionInfo createLogin() {

        ActionExec action = ActionExec.ActionFactory.createAction("login");

        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the Register Action
     * @return
     */
    public static ActionInfo createRegister() {

        ActionExec action = ActionExec.ActionFactory.createAction("register");

        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the Search Action
     * @return
     */
    public static ActionInfo createSearch() {

        ActionExec action = ActionExec.ActionFactory.createAction("search");

        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }
    /**
     * Method to initialise the Filter Action
     * @return
     */
    public static ActionInfo createFilter() {

        ActionExec action = ActionExec.ActionFactory.createAction("filter");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the Purchase Action
     * @return
     */
    public static ActionInfo createPurchase() {

        ActionExec action = ActionExec.ActionFactory.createAction("purchase");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the Watch Action
     * @return
     */
    public static ActionInfo createWatch() {

        ActionExec action = ActionExec.ActionFactory.createAction("watch");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the Like Action
     * @return
     */
    public static ActionInfo createLike() {

        ActionExec action = ActionExec.ActionFactory.createAction("like");

        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }
    /**
     * Method to initialise the Rate Action
     * @return
     */
    public static ActionInfo createRate() {

        ActionExec action = ActionExec.ActionFactory.createAction("rate");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }
    /**
     * Method to initialise the Buy premium account Action
     * @return
     */
    public static ActionInfo createBuyPremiumAccount() {

        ActionExec action = ActionExec.ActionFactory.createAction("buy premium account");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }
    /**
     * Method to initialise the Buy tokens Action
     * @return
     */
    public static ActionInfo createBuyTokens() {

        ActionExec action = ActionExec.ActionFactory.createAction("buy tokens");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }

    /**
     * Method to initialise the subscribe Action
     * @return
     */
    public static ActionInfo createSubscribe() {

        ActionExec action = ActionExec.ActionFactory.createAction("subscribe");
        ActionInfo actionInfo = new ActionInfo(action);

        return actionInfo;
    }
    /**
     * Method to create the Hashmap with all the actions
     * a mapper from the action name to the action that can be made
     * @return
     */
    public static HashMap<String, ActionInfo> createActions() {
        HashMap<String, ActionInfo> actions = new HashMap<String, ActionInfo>();

        actions.put("login", createLogin());
        actions.put("register", createRegister());
        actions.put("search", createSearch());
        actions.put("filter", createFilter());
        actions.put("purchase", createPurchase());
        actions.put("watch", createWatch());
        actions.put("like", createLike());
        actions.put("rate", createRate());
        actions.put("buy premium account", createBuyPremiumAccount());
        actions.put("buy tokens", createBuyTokens());
        actions.put("subscribe", createSubscribe());

        return actions;
    }
}
