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

public class BuyTokens extends ActionExec {

    public BuyTokens() { }

    /** inherited method to buy tokens
     * if the user has enough balance
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

        int balance = Integer.parseInt(currentUser.getCredentials().getBalance());

        if (Integer.parseInt(count) > balance) {
            WriteUtils.generalError();
            return;
        }

        currentUser.setTokensCount(currentUser.getTokensCount() + Integer.parseInt(count));
        currentUser.getCredentials().setBalance(String.valueOf(balance - Integer.parseInt(count)));
    }
}
