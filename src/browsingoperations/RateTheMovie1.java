package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class RateTheMovie1 extends ActionBuilder {

    private static final int MINIMUM_RATING = 1;
    private static final int MAXIMUM_RATING = 5;
    private static RateTheMovie1 instance = null;

    private static ActionBuilder actionBuilder;

    private RateTheMovie1() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param movieName
     * @param rate
     * @return
     */
    public static RateTheMovie1 getInstance(final ArrayList<Movie> movieList,
                                            final User currentUser,
                                            final String movieName,
                                            final int rate) {

        if (instance == null) {
            instance = new RateTheMovie1();
            actionBuilder = new Builder("rating")
                    .movieList(movieList)
                    .currentUser(currentUser)
                    .currentMovie(movieName)
                    .rate(rate)
                    .build();
        } else {
            actionBuilder.setMovieList(movieList);
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setCurrentMovie(movieName);
            actionBuilder.setRate(rate);
        }

        return instance;
    }

    @Override
    public void executeAction() {

        Movie selectedMovie = actionBuilder.getMovieList().get(0);
        instance.setCurrentUser(actionBuilder.getCurrentUser());

        if (actionBuilder.getCurrentMovie().compareTo(selectedMovie.getName()) == 0
                && actionBuilder.getRate() >= MINIMUM_RATING
                && actionBuilder.getRate() <= MAXIMUM_RATING) {

            instance.getCurrentUser().getRatedMovies().add(selectedMovie);
            selectedMovie.setRating(actionBuilder.getRate() + selectedMovie.getRating());
            selectedMovie.setNumRatings(selectedMovie.getNumRatings() + 1);

            WriteUtils.noError(actionBuilder.getMovieList(), instance.getCurrentUser());

        } else {
            WriteUtils.generalError();
        }

        instance.setCurrentUser(actionBuilder.getCurrentUser());
    }
    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }

}
