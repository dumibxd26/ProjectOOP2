package browsingoperations;

import browsingoperations.utils.BrowsingUtils;
import browsingoperations.utils.WriteUtils;
import initializations.ActionInfo;
import notificationsobserver.Notifications;
import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Filter extends ActionExec {

    public Filter() { }

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

        ArrayList<Movie> newMovieList = BrowsingUtils.newArr(notUserBannedMovies);

        if (filters.getContains() != null) {
            ArrayList<String> actors = filters.getContains().getActors();
            ArrayList<String> genre = filters.getContains().getGenre();

            newMovieList = BrowsingUtils.filterContains(newMovieList, actors, genre);
        }

        if (filters.getSort() != null) {
            if (filters.getSort().getRating() != null
                    && filters.getSort().getDuration() == null) {

                newMovieList = BrowsingUtils.sortByRating(newMovieList,
                        filters.getSort().getRating());
            } else if (filters.getSort().getDuration() != null
                    && filters.getSort().getRating() == null) {

                newMovieList = BrowsingUtils.sortByDuration(newMovieList,
                        filters.getSort().getDuration());
            } else if (filters.getSort().getDuration() != null
                    && filters.getSort().getRating() != null) {

                newMovieList = BrowsingUtils.sortByBoth(newMovieList,
                        filters.getSort().getRating(), filters.getSort().getDuration());
            }
        }

        WriteUtils.noError(newMovieList, currentUser);
        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("filter")
                    .filteredList(newMovieList)
                    .build();

        } else {
            actionParameters.setFilteredList(newMovieList);
        }
    }
}
