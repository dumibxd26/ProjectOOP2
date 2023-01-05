package browsingoperations;

import initializations.ActionInfo;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Rate extends ActionExec {

    private static final int MINIMUM_RATING = 1;
    private static final int MAXIMUM_RATING = 5;
    public Rate() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        HashMap<String, ActionInfo> actions) {

        if (previousAction == null
                || actions.get(previousAction).getNextActions().contains("rate") == false) {
            WriteUtils.generalError();
            return;
        }

        Movie selectedMovie = filteredList.get(0);

        if (currentMovie != null && selectedMovie.getName().compareTo(currentMovie) != 0) {
            WriteUtils.generalError();
            return;
        }

        int intRate = Integer.parseInt(rate);

        if (intRate < MINIMUM_RATING || intRate > MAXIMUM_RATING) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getRatedMovies().add(selectedMovie);
        selectedMovie.setNumRatings(selectedMovie.getNumRatings() + 1);
        selectedMovie.setRating(selectedMovie.getRating() + intRate);

        WriteUtils.noError(filteredList, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("rate")
                    .previousAction("rate")
                    .build();
        } else {
            actionParameters.setPreviousAction("rate");
        }

    }
}
