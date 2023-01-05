package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends ActionExec {

        public Login() { }

        @Override
        public void execute(final User currentUser, final String previousAction,
                            final String currentMovie, final ArrayList<Movie> movieList,
                            final ArrayList<User> userList, final String startsWith,
                            final String count, final String rate, final Credentials credentials,
                            final Filters filters, final ArrayList<Movie> filteredList,
                            final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                            final String deletedMovie, final String currentPage,
                            HashMap<String, ActionInfo> actions) {

            User user = BrowsingUtils.checkUserExistence(userList,
                        credentials);

            if (user == null || currentUser != null) {
                WriteUtils.generalError();
                return;
            }

            String country = user.getCredentials().getCountry();

            ArrayList<Movie> notBanned = BrowsingUtils
                    .filterCountry(movieList, country);
            WriteUtils.userLogin(user);

            if (actionParameters == null) {
                actionParameters = new ActionBuilder.Builder("login")
                        .currentUser(user)
                        .notUserBannedMovies(notBanned)
                        .filteredList(notBanned)
                        .currentPage("homepage autentificat")
                        .previousAction("login")
                        .build();


            } else {
                actionParameters.setCurrentUser(user);
                actionParameters.setNotUserBannedMovies(notBanned);
                actionParameters.setFilteredList(notBanned);
                actionParameters.setCurrentPage("homepage autentificat");
                actionParameters.setPreviousAction("login");
            }

        }
}
