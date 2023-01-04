package browsingoperations;

import readinput.Credentials;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Login extends ActionBuilder {

    private static Login instance = null;
    private static ActionBuilder actionBuilder;
    private Login() { }

    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param userList
     * @param currentUser
     * @param credentials
     * @return
     */
    public static Login getInstance(final ArrayList<Movie> movieList,
                                    final ArrayList<User> userList,
                                    final User currentUser,
                                    final Credentials credentials) {

        if (instance == null) {
            instance = new Login();

            actionBuilder = new Builder("login")
                    .movieList(movieList)
                    .userList(userList)
                    .currentUser(currentUser)
                    .credentials(credentials)
                    .build();
        } else {
            actionBuilder.setMovieList(movieList);
            actionBuilder.setCredentials(credentials);
            actionBuilder.setUserList(userList);
            actionBuilder.setCurrentUser(currentUser);
        }

        return instance;
    }

    /**
     * If the user can be found in the userlist and there
     * is no other user logged in
     * login
     */
    @Override
    public void executeAction() {

        User user = BrowsingUtils.checkUserExistence(actionBuilder.getUserList(),
                actionBuilder.getCredentials());

        if (user != null && actionBuilder.getCurrentUser() == null) {
            actionBuilder.setCurrentUser(user);

            String country = user.getCredentials().getCountry();

            ArrayList<Movie> filteredList = BrowsingUtils
                    .filterCountry(actionBuilder.getMovieList(), country);

            instance.setMovieList(filteredList);
            actionBuilder.setMovieList(filteredList);

            WriteUtils.userLogin(actionBuilder.getCurrentUser());
            instance.setCurrentUser(actionBuilder.getCurrentUser());

        } else {
            WriteUtils.generalError();
            actionBuilder.setMovieList(actionBuilder.getMovieList());
        }

    }
    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }

}
