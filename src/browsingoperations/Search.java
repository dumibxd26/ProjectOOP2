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

public class Search extends ActionExec {

    public Search() { }

    /**
     * inherited method to execute the action
     * find the movies that start with the given string
     * add them to the filtered list
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

        ArrayList<Movie> filteredListFunc = BrowsingUtils
                .filterStartsWith(filteredList, startsWith);

        WriteUtils.noError(filteredListFunc, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("search")
                    .filteredList(filteredListFunc)
                    .build();
        } else {
            actionParameters.setFilteredList(filteredListFunc);
        }
    }
}
