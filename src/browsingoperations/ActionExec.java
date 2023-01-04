package browsingoperations;

import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public abstract class ActionExec {

    ActionBuilder actionParameters = null;

    public abstract void execute(User currentUser, String previousAction, String currentMovie,
                            ArrayList<Movie> movieList, ArrayList<User> userList,
                            String startsWith, String count, String rate, Credentials credentials,
                            Filters filters, Movie addedMovie, String deletedMovie);

    public ActionBuilder getActionParameters() {
        return actionParameters;
    }

    public class ActionFactory {

        public static ActionExec createAction(final String actionType) {

            switch (actionType) {
                case "Like":
                    return new Like();
//            case "favorite":
//                return new Favorite();
//            case "view":
//                return new View();
//            case "rating":
//                return new Rating();
//            case "search":
//                return new Search();
//            case "command":
//                return new Command();
//            case "like":
//                return new Like();
//            case "recommendation":
//                return new Recommendation();
//            case "show":
//                return new Show();
                default:
                    return null;
            }
        }
    }
}

