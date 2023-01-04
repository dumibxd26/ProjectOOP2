package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Watch extends ActionBuilder  {

    private static Watch instance = null;

    private static ActionBuilder actionBuilder;

    private Watch() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param movieName
     * @return
     */
    public static Watch getInstance(final ArrayList<Movie> movieList,
                                    final User currentUser,
                                    final String movieName) {

        if (instance == null) {
            instance = new Watch();
            actionBuilder = new Builder("watch")
                    .movieList(movieList)
                    .currentUser(currentUser)
                    .currentMovie(movieName).build();
        } else {
            actionBuilder.setMovieList(movieList);
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setCurrentMovie(movieName);
        }

        return instance;
    }

    @Override
    public void executeAction() {

        Movie selectedMovie = actionBuilder.getMovieList().get(0);
        instance.setCurrentUser(actionBuilder.getCurrentUser());

        if (actionBuilder.getCurrentMovie().compareTo(selectedMovie.getName()) == 0) {

            instance.getCurrentUser().getWatchedMovies().add(selectedMovie);

            WriteUtils.noError(actionBuilder.getMovieList(), instance.getCurrentUser());

        } else {
            WriteUtils.generalError();
        }
    }
    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }
}
