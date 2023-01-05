package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyTokens extends ActionExec{

    public BuyTokens() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        int balance = Integer.parseInt(currentUser.getCredentials().getBalance());

        if (Integer.parseInt(count) > balance) {
            WriteUtils.generalError();
            return;
        }

        currentUser.setTokensCount(currentUser.getTokensCount() + Integer.parseInt(count));
        currentUser.getCredentials().setBalance(String.valueOf(balance - Integer.parseInt(count)));

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("buy tokens")
                    .currentUser(currentUser)
                    .build();
        } else {
            actionParameters.setCurrentUser(currentUser);
        }

    }
}
