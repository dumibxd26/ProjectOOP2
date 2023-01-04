package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Like1 extends ActionBuilder {

    private static Like1 instance = null;

    private static ActionBuilder actionBuilder;

    private Like1() { }

    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param movieName
     * @return
     */
    public static Like1 getInstance(final ArrayList<Movie> movieList,
                                    final User currentUser,
                                    final String movieName) {

        if (instance == null) {
            instance = new Like1();
            actionBuilder = new Builder("like")
                    .movieList(movieList)
                    .currentUser(currentUser)
                    .currentMovie(movieName)
                    .build();
        } else {
            actionBuilder.setMovieList(movieList);
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setCurrentMovie(movieName);
        }

        return instance;
    }

    /**
     * Check if the movie valid(this is because of a test case error)
     * and like it
     */
    @Override
    public void executeAction() {

        Movie selectedMovie = actionBuilder.getMovieList().get(0);
        instance.setCurrentUser(actionBuilder.getCurrentUser());

        if (actionBuilder.getCurrentMovie().compareTo(selectedMovie.getName()) == 0) {

            instance.getCurrentUser().getLikedMovies().add(selectedMovie);

            selectedMovie.setNumLikes(selectedMovie.getNumLikes() + 1);

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
