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

public class Rate extends ActionExec {

    private static final int MINIMUM_RATING = 1;
    private static final int MAXIMUM_RATING = 5;
    public Rate() { }

    /**
     * inherited function for executing the action rate
     * if the movie is not watched, throw error
     * it the movie was already rated, change the rate,
     * and modify its rating for the movie in the hashset userMovieRatings
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

        Movie selectedMovie = filteredList.get(0);

        Boolean watched = false;
        for (Movie movie : currentUser.getWatchedMovies()) {
            if (movie.getName().compareTo(selectedMovie.getName()) == 0) {
                watched = true;
                break;
            }
        }

        if (!watched) {
            WriteUtils.generalError();
            return;
        }

        if (currentMovie != null && selectedMovie.getName().compareTo(currentMovie) != 0) {
            WriteUtils.generalError();
            return;
        }

        int intRate = Integer.parseInt(rate);

        if (intRate < MINIMUM_RATING || intRate > MAXIMUM_RATING) {
            WriteUtils.generalError();
            return;
        }

        if (userMovieRatings.get(currentUser) == null) {
            userMovieRatings.put(currentUser, new HashMap<>());
        }

        if (!currentUser.getRatedMovies().contains(selectedMovie)) {
            currentUser.getRatedMovies().add(selectedMovie);
            selectedMovie.setNumRatings(selectedMovie.getNumRatings() + 1);
        } else {
            selectedMovie.setRating(selectedMovie.getRating()
                    - userMovieRatings.get(currentUser).get(selectedMovie));
        }

        selectedMovie.setRating(selectedMovie.getRating() + intRate);
        userMovieRatings.get(currentUser).put(selectedMovie, intRate);

        WriteUtils.noError(filteredList, currentUser);
    }
}
