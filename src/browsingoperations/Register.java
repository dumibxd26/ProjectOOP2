package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Register extends ActionExec {

    public Register() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        User user = BrowsingUtils.checkUserExistence(userList, credentials);

        if (user != null) {
            WriteUtils.generalError();
            return;
        }

        User newUser = new User(credentials);
        userList.add(newUser);
        WriteUtils.userRegister(newUser);

        ArrayList<Movie> notBanned = BrowsingUtils
                .filterCountry(movieList, newUser.getCredentials().getCountry());

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("register")
                    .currentUser(newUser)
                    .userList(userList)
                    .currentPage("homepage autentificat")
                    .previousAction("register")
                    .notUserBannedMovies(notBanned)
                    .filteredList(notBanned)
                    .build();
        } else {
            actionParameters.setCurrentUser(newUser);
            actionParameters.setUserList(userList);
            actionParameters.setCurrentPage("homepage autentificat");
            actionParameters.setPreviousAction("register");
            actionParameters.setNotUserBannedMovies(notBanned);
            actionParameters.setFilteredList(notBanned);
        }

    }
}
