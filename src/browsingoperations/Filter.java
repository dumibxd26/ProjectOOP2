package browsingoperations;

import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class Filter extends ActionBuilder  {

    private static Filter instance = null;
    private static ActionBuilder actionBuilder;

    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param movieList
     * @param currentUser
     * @param filter
     * @return
     */
    public static Filter getInstance(final ArrayList<Movie> movieList,
                                     final User currentUser,
                                     final Filters filter) {
        if (instance == null) {
            instance = new Filter();

            actionBuilder = new Builder("search").movieList(movieList)
                    .filters(filter)
                    .currentUser(currentUser)
                    .build();
        } else {
            actionBuilder.setFilters(filter);
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setMovieList(movieList);
        }

        instance.setMovieList(movieList);

        return instance;
    }

    /**
     * Check the existance of the filters and
     * sort / filter the movies using either streams or normal for loops
     */
    @Override
    public void executeAction() {

        ArrayList<Movie> newMovieList = BrowsingUtils.newArr(actionBuilder.getMovieList());

        Filters filters = actionBuilder.getFilters();

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

        WriteUtils.noError(newMovieList, actionBuilder.getCurrentUser());

        actionBuilder.setMovieList(newMovieList);
        instance.setMovieList(newMovieList);
    }

    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }
}
