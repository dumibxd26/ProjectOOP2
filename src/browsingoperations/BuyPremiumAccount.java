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

public class BuyPremiumAccount extends ActionExec {

    private static final int PREMIUMACCOUNTPRICE = 10;

    public BuyPremiumAccount() { }

    /**
     * inherited method to buy premium account
     * if the user has enough tokens(10)
     * the user will be upgraded to premium account
     * otherwise write error
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

        int tokens = currentUser.getTokensCount();

        if (tokens < PREMIUMACCOUNTPRICE
             || currentUser.getCredentials().getAccountType().compareTo("premium") == 0) {
              WriteUtils.generalError();
              return;
        }

        currentUser.setTokensCount(tokens - PREMIUMACCOUNTPRICE);
        currentUser.getCredentials().setAccountType("premium");

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("buy premium account")
                    .currentUser(currentUser)
                    .build();
        } else {
            actionParameters.setCurrentUser(currentUser);
        }

    }
}
