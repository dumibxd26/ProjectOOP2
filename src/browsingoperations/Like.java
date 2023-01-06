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

        Movie selectedMovie = filteredList.get(0);

        Boolean watched = false;
        for (Movie movie : currentUser.getWatchedMovies())
            if (movie.getName().compareTo(selectedMovie.getName()) == 0) {
                watched = true;
                break;
            }

        if (watched == false) {
            WriteUtils.generalError();
            return;
        }

        if (currentMovie != null && selectedMovie.getName().compareTo(currentMovie) != 0) {
            WriteUtils.generalError();
            return;
        }

        currentUser.getLikedMovies().add(selectedMovie);
        selectedMovie.setNumLikes(selectedMovie.getNumLikes() + 1);

        WriteUtils.noError(filteredList, currentUser);

        // mai bine nu faci ptc in primu rand e fara sens
        // in al doilea rand, daca actiunea da fail ptc nu se poate vedea
        // se opreste inainte sa se modifice asta
        // si lista filtrata nu apuca sa se modifice si se ia cea de la
        // like ul dinainte cu useru dinainte
//        if (actionParameters == null) {
//            actionParameters = new ActionBuilder.Builder("like")
//                    .filteredList(filteredList)
//                    .build();
//        } else {
//            actionParameters.setFilteredList(filteredList);
//        }
    }
}
