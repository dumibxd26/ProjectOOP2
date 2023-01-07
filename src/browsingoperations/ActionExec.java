package browsingoperations;

import initializations.ActionInfo;
import notificationsobserver.Notifications;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ActionExec {

    protected ActionBuilder actionParameters = null;

    /**
     * function meant to be overriden by the child classes
     * to implement their functionality
     */
    public abstract void execute(User currentUser,
                                 String currentMovie, ArrayList<Movie> movieList,
                                 ArrayList<User> userList, String startsWith,
                                 String count, String rate, Credentials credentials,
                                 Filters filters, ArrayList<Movie> filteredList,
                                 ArrayList<Movie> notUserBannedMovies, Movie addedMovie,
                                 String deletedMovie, String currentPage,
                                 HashMap<String, ActionInfo> actions,
                                 HashMap<User, HashMap<Movie, Integer>> userMovieRatings,
                                 String subscribedGenre, Notifications notifications);

    /**
     * getter for the actionParameters
     * @return
     */
    public ActionBuilder getActionParameters() {
        return actionParameters;
    }

    /**
     * Factory class for creating the action objects
     */
    public class ActionFactory {
        /**
         * method that return the desired action
         * for the input
         * @param actionType
         * @return the action object
         */
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
                case "subscribe":
                    return new Subscribe();
                default:
                    return null;
            }
        }
    }
}

