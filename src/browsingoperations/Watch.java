package browsingoperations;

import browsingoperations.utils.WriteUtils;
import initializations.ActionInfo;
import notificationsobserver.Notifications;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Watch extends ActionExec{

    public Watch() { }

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

        Movie movie = filteredList.get(0);

        if (currentUser.getPurchasedMovies().contains(movie) == false) {
            WriteUtils.generalError();
            return;
        }

        if (currentMovie != null && currentMovie.compareTo(movie.getName()) != 0) {
            WriteUtils.generalError();
            return;
        }

        // Check if the movie was already watched
        // not to add it again in the list of watched movies
        if (currentUser.getWatchedMovies().contains(movie) == false) {
            currentUser.getWatchedMovies().add(movie);
        }

        WriteUtils.noError(filteredList, currentUser);

    }
}
