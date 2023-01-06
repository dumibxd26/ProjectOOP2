package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Watch extends ActionExec{

    public Watch() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        Movie movie = filteredList.get(0);

        if (previousAction == null
                || currentUser.getPurchasedMovies().contains(movie) == false) {
            WriteUtils.generalError();
            return;
        }

        if (currentMovie != null && currentMovie.compareTo(movie.getName()) != 0) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getWatchedMovies().add(movie);
        WriteUtils.noError(filteredList, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("watch")
                    .previousAction("watch")
                    .build();

        } else {
            actionParameters.setPreviousAction("watch");
        }
    }
}
