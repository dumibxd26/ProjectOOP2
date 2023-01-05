package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyPremiumAccount extends ActionExec{

    private static final int PREMIUMACCOUNTPRICE = 10;

    public BuyPremiumAccount() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

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
