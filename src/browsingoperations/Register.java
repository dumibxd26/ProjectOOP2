package browsingoperations;

import browsingoperations.utils.BrowsingUtils;
import browsingoperations.utils.WriteUtils;
import initializations.ActionInfo;
import notificationsobserver.Notifications;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Register extends ActionExec {

    public Register() { }

    /**
     * inherited method from ActionExec
     * to register a user
     * if the user already exist, print error
     * otherwise create the user
     * add it to the database
     * register it as new observer
     * return the new lists
     * current user and page
     */
    @Override
    public void execute(final User currentUser,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        final HashMap<String, ActionInfo> actions,
                        final HashMap<User, HashMap<Movie, Integer>> userMovieRatings,
                        final String subscribedGenre, final Notifications notifications) {

        User user = BrowsingUtils.checkUserExistence(userList, credentials);

        if (user != null) {
            WriteUtils.generalError();
            return;
        }

        User newUser = new User(credentials);
        userList.add(newUser);
        WriteUtils.userRegister(newUser);

        ArrayList<Movie> notBanned = BrowsingUtils
                .filterCountry(movieList, newUser.getCredentials().getCountry());

        notifications.registerObserver(newUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("register")
                    .currentUser(newUser)
                    .currentPage("homepage autentificat")
                    .notUserBannedMovies(notBanned)
                    .filteredList(notBanned)
                    .build();
        } else {
            actionParameters.setCurrentUser(newUser);
            actionParameters.setCurrentPage("homepage autentificat");
            actionParameters.setNotUserBannedMovies(notBanned);
            actionParameters.setFilteredList(notBanned);
        }
    }
}
