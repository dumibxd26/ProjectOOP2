package browsingoperations;

import browsingoperations.utils.WriteUtils;
import initializations.ActionInfo;
import notificationsobserver.Notifications;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Like extends ActionExec {

    public Like() { }

    @Override
    public void execute(final User currentUser,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final ArrayList<Movie> filteredList,
                        final ArrayList<Movie> notUserBannedMovies, final Movie addedMovie,
                        final String deletedMovie, final String currentPage,
                        final HashMap<String, ActionInfo> actions,
                        final HashMap<User, HashMap<Movie, Integer>> userMovieRatings,
                        final String subscribedGenre, final Notifications notifications) {

        if (currentUser.getWatchedMovies().contains(filteredList.get(0)) == false) {
            WriteUtils.generalError();
            return;
        }

        Movie selectedMovie = filteredList.get(0);

        if (currentMovie != null && selectedMovie.getName().compareTo(currentMovie) != 0) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getLikedMovies().add(selectedMovie);
        selectedMovie.setNumLikes(selectedMovie.getNumLikes() + 1);

        WriteUtils.noError(filteredList, currentUser);

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("like")
                    .filteredList(filteredList)
                    .build();
        } else {
            actionParameters.setFilteredList(filteredList);
        }
    }
}
