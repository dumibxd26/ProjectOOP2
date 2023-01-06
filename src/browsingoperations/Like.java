package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Like extends ActionExec {

    public Like() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        if (previousAction == null
                || currentUser.getWatchedMovies().contains(filteredList.get(0)) == false) {
            WriteUtils.generalError();
            return;
        }

        Movie selectedMovie = filteredList.get(0);

        if (currentMovie != null && selectedMovie.getName().compareTo(currentMovie) != 0) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getLikedMovies().add(selectedMovie);
        selectedMovie.setNumLikes(selectedMovie.getNumLikes() + 1);

        WriteUtils.noError(filteredList, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("like")
                    .previousAction("like")
                    .filteredList(filteredList)
                    .build();
        } else {
            actionParameters.setPreviousAction("like");
            actionParameters.setFilteredList(filteredList);
        }
    }
}
