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

public class Subscribe extends ActionExec {

    public Subscribe() { }

    /**
     * inherited method to execute the action
     * if the user is not already subscribed to the genre
     * and the movie contains the desired genre
     * subscribe to the genre
     * adding the movie to the list of subscribed genres
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

        Movie movie = filteredList.get(0);

        if (!movie.getGenres().contains(subscribedGenre)
            || currentUser.getSubscribedGenres().contains(subscribedGenre)) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getSubscribedGenres().add(subscribedGenre);

    }
}
