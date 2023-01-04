package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Search1 extends ActionBuilder  {

    private static Search1 instance = null;

    private static ActionBuilder actionBuilder;
    private Search1() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param startsWith
     * @return
     */
    public static Search1 getInstance(final ArrayList<Movie> movieList,
                                      final User currentUser,
                                      final String startsWith) {
        if (instance == null) {
            instance = new Search1();

            actionBuilder = new Builder("search")
                    .movieList(movieList)
                    .startsWith(startsWith)
                    .currentUser(currentUser)
                    .build();
        } else {
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setMovieList(movieList);
            actionBuilder.setStartsWith(startsWith);
        }

        instance.setMovieList(movieList);

        return instance;
    }

    /**
     * Using a stream filter, find the movies that start with the given string
     */
    @Override
    public void executeAction() {

        ArrayList<Movie> movieList = actionBuilder.getMovieList();

        // Make a stream to filter the movies that name's start with the given string
        movieList = BrowsingUtils1.filterStartsWith(movieList, actionBuilder.getStartsWith());

        WriteUtils.noError(movieList, actionBuilder.getCurrentUser());

        actionBuilder.setMovieList(movieList);
    }
    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }
}