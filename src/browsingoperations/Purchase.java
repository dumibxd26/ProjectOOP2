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

public class Purchase extends ActionExec{

    public Purchase() { }

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

        if (currentMovie != null && currentMovie.compareTo(selectedMovie.getName()) != 0) {
            WriteUtils.generalError();
            return;
        }

        for (Movie movie : currentUser.getPurchasedMovies()) {
            if (movie.getName().compareTo(selectedMovie.getName()) == 0) {
                WriteUtils.generalError();
                return;
            }
        }

        if (currentUser.getCredentials().getAccountType().compareTo("premium") == 0
                && currentUser.getNumFreePremiumMovies() != 0) {

            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
            currentUser.setTokensCount(currentUser.getTokensCount() + 2);
        }

        if (currentUser.getTokensCount() >= 2) {

            currentUser.setTokensCount(currentUser.getTokensCount() - 2);
            currentUser.getPurchasedMovies().add(selectedMovie);

            WriteUtils.noError(filteredList, currentUser);
        } else {
            WriteUtils.generalError();
            return;
        }

    }
}
