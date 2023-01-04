package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Purchase1 extends ActionBuilder {

    private static Purchase1 instance = null;

    private static ActionBuilder actionBuilder;

    private Purchase1() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param movieName
     * @return
     */
    public static Purchase1 getInstance(final ArrayList<Movie> movieList,
                                        final User currentUser,
                                        final String movieName) {

        if (instance == null) {
            instance = new Purchase1();
            actionBuilder = new Builder("purchase")
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

    /**
     * If the user has enough tokens, buy the movie
     * If the user is premium, one of the free films
     * if automatically transformed into 2 tokens
     * then checks if there is enough money to buy the movie
     */
    @Override
    public void executeAction() {

        Movie selectedMovie = actionBuilder.getMovieList().get(0);

        instance.setCurrentUser(actionBuilder.getCurrentUser());

         if (actionBuilder.getCurrentMovie().compareTo(selectedMovie.getName()) == 0) {

             if (instance.getCurrentUser().getCredentials().getAccountType().
                     compareTo("premium") == 0
                     && instance.getCurrentUser().getNumFreePremiumMovies() != 0) {

                 instance.getCurrentUser().setNumFreePremiumMovies(instance.getCurrentUser().
                         getNumFreePremiumMovies() - 1);
                 instance.getCurrentUser().setTokensCount(instance.getCurrentUser()
                         .getTokensCount() + 2);
             }

             if (instance.getCurrentUser().getTokensCount() >= 2) {

                 instance.getCurrentUser().setTokensCount(instance.getCurrentUser()
                         .getTokensCount() - 2);
                 instance.getCurrentUser().getPurchasedMovies().add(selectedMovie);

                 WriteUtils.noError(actionBuilder.getMovieList(), instance.getCurrentUser());
             } else {
                    WriteUtils.generalError();
             }
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
