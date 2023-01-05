package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ActionExec {

    ActionBuilder actionParameters = null;

    public abstract void execute(final User currentUser, final String previousAction,
                                 final String currentMovie, final ArrayList<Movie> movieList,
                                 final ArrayList<User> userList, final String startsWith,
                                 final String count, final String rate, final Credentials credentials,
                                 final Filters filters, final ArrayList<Movie> filteredList,
                                 final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                                 final String deletedMovie, final String currentPage,
                                 HashMap<String, ActionInfo> actions);

    public ActionBuilder getActionParameters() {
        return actionParameters;
    }

    public class ActionFactory {

        public static ActionExec createAction(final String actionType) {

            switch (actionType) {
                case "login":
                    return new Login();
                case "register":
                    return new Register();
                case "like":
                    return new Like();
                case "search":
                    return new Search();
                case "filter":
                    return new Filter();
                case "purchase":
                    return new Purchase();
                case "buy tokens":
                    return new BuyTokens();
                case "buy premium account":
                    return new BuyPremiumAccount();
                case "watch":
                    return new Watch();
                case "rate":
                    return new Rate();
                default:
                    return null;
            }
        }
    }
}

