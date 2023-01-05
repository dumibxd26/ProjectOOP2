package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Search extends ActionExec{

    public Search() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        ArrayList<Movie> filteredListFunc = BrowsingUtils.filterStartsWith(filteredList, startsWith);

        WriteUtils.noError(filteredListFunc, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("search")
                    .currentUser(currentUser)
                    .filteredList(filteredListFunc)
                    .previousAction("search")
                    .build();
        } else {
            actionParameters.setCurrentUser(currentUser);
            actionParameters.setFilteredList(filteredListFunc);
            actionParameters.setPreviousAction("search");
        }
    }
}
