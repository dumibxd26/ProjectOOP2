package initializations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import readinput.Movie;
import browsingoperations.ActionBuilder;
import browsingoperations.ActionExec;


// Maybe, in the future, actions can be implemented with
// other previous actions before, and this makes the job easier.
public final class ActionsUtils {

    private ActionsUtils() { }

    public static ArrayList<Movie> movieList;

    /**
     * Method to initialise the Login Action
     * @return
     */
    public static ActionInfo createLogin() {
        HashSet<String> loginSetNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("login");

        ActionInfo actionInfo = new ActionInfo(loginSetNext, action);

        return actionInfo;
    }

    /**
     * Method to initialise the Register Action
     * @return
     */
    public static ActionInfo createRegister() {
        HashSet<String> registerSetNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("register");

        ActionInfo actionInfo = new ActionInfo(registerSetNext, action);

        return actionInfo;
    }

    /**
     * Method to initialise the Search Action
     * @return
     */
    public static ActionInfo createSearch() {
        HashSet<String> searchSetNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("search");

        ActionInfo actionInfo = new ActionInfo(searchSetNext, action);

        return actionInfo;
    }
    /**
     * Method to initialise the Filter Action
     * @return
     */
    public static ActionInfo createFilter() {
        HashSet<String> filterSetNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("filter");
        ActionInfo actionInfo = new ActionInfo(filterSetNext, action);

        return actionInfo;
    }

    /**
     * Method to initialise the Purchase Action
     * @return
     */
    public static ActionInfo createPurchase() {
        HashSet<String> purchaseNext = new HashSet<String>();

        purchaseNext.add("watch");

        ActionExec action = ActionExec.ActionFactory.createAction("purchase");
        ActionInfo actionInfo = new ActionInfo(purchaseNext, action);

        return actionInfo;
    }

    /**
     * Method to initialise the Watch Action
     * @return
     */
    public static ActionInfo createWatch() {
        HashSet<String> watchNext = new HashSet<String>();

        watchNext.add("like");
        watchNext.add("rate");

        ActionExec action = ActionExec.ActionFactory.createAction("watch");
        ActionInfo actionInfo = new ActionInfo(watchNext, action);

        return actionInfo;
    }

    /**
     * Method to initialise the Like Action
     * @return
     */
    public static ActionInfo createLike() {
        HashSet<String> registerSetNext = new HashSet<String>();

        registerSetNext.add("rate");

        ActionExec action = ActionExec.ActionFactory.createAction("like");

        ActionInfo actionInfo = new ActionInfo(registerSetNext, action);

        return actionInfo;
    }
    /**
     * Method to initialise the Rate Action
     * @return
     */
    public static ActionInfo createRate() {
        HashSet<String> rateMovieNext = new HashSet<String>();

        rateMovieNext.add("like");

        ActionExec action = ActionExec.ActionFactory.createAction("rate");
        ActionInfo actionInfo = new ActionInfo(rateMovieNext, action);

        return actionInfo;
    }
    /**
     * Method to initialise the Buy premium account Action
     * @return
     */
    public static ActionInfo createBuyPremiumAccount() {
        HashSet<String> buyPremiumAccountNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("buy premium account");
        ActionInfo actionInfo = new ActionInfo(buyPremiumAccountNext, action);

        return actionInfo;
    }
    /**
     * Method to initialise the Buy tokens Action
     * @return
     */
    public static ActionInfo createBuyTokens() {
        HashSet<String> buyTokensNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("buy tokens");
        ActionInfo actionInfo = new ActionInfo(buyTokensNext, action);

        return actionInfo;
    }

    public static ActionInfo createSubscribe() {
        HashSet<String> subscribeNext = new HashSet<String>();

        ActionExec action = ActionExec.ActionFactory.createAction("subscribe");
        ActionInfo actionInfo = new ActionInfo(subscribeNext, action);

        return actionInfo;
    }
    /**
     * Method to create the Hashmap with all the actions
     * a mapper from the action name to the action that can be made
     * @return
     */
    public static HashMap<String, ActionInfo> createActions(final ArrayList<Movie> movies) {
        HashMap<String, ActionInfo> actions = new HashMap<String, ActionInfo>();

        movieList = movies;

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
