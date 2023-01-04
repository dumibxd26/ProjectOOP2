package browsingoperations;

import readinput.Credentials;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Register1 extends ActionBuilder {

    private static Register1 instance = null;

    private static ActionBuilder actionBuilder;
    private Register1() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param userList
     * @param credentials
     * @return
     */
    public static Register1 getInstance(final ArrayList<Movie> movieList,
                                        final ArrayList<User> userList,
                                        final Credentials credentials) {
        String country = credentials.getCountry();

        ArrayList<Movie> filteredList = BrowsingUtils1.filterCountry(movieList, country);

        if (instance == null) {
            instance = new Register1();

            actionBuilder = new Builder("register")
                    .movieList(filteredList)
                    .userList(userList)
                    .credentials(credentials)
                    .build();
        } else {
            actionBuilder.setMovieList(filteredList);
            actionBuilder.setUserList(userList);
            actionBuilder.setCredentials(credentials);
        }

        instance.setMovieList(filteredList);

        return instance;
    }

    @Override
    public void executeAction() {

        User user = BrowsingUtils1.checkUserExistence(actionBuilder.getUserList(),
                actionBuilder.getCredentials());

        if (user != null) {
            WriteUtils.generalError();
            instance.setCurrentUser(null);
            actionBuilder.setCurrentUser(null);
        } else {
            User newUser = new User(actionBuilder.getCredentials());
            actionBuilder.getUserList().add(newUser);
            actionBuilder.setCurrentUser(newUser);
            WriteUtils.userRegister(newUser);

            instance.setCurrentUser(newUser);
            instance.setMovieList(actionBuilder.getMovieList());
            instance.setUserList(actionBuilder.getUserList());
        }
    }
    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }
}
