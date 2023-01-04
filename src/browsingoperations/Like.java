package browsingoperations;

import readinput.Credentials;
import readinput.Filters;
import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public class Like extends ActionExec {

    public Like() { }

    @Override
    public void execute(final User currentUser, final String previousAction,
                        final String currentMovie, final ArrayList<Movie> movieList,
                        final ArrayList<User> userList, final String startsWith,
                        final String count, final String rate, final Credentials credentials,
                        final Filters filters, final Movie addedMovie, final String deletedMovie) {

        if (actionParameters == null) {
            actionParameters = new ActionBuilder.Builder("like")
                    .movieList(movieList)
                    .currentUser(currentUser)
                    .currentMovie(currentMovie)
                    .build();
        } else {
            actionParameters.setMovieList(movieList);
            actionParameters.setCurrentUser(currentUser);
            actionParameters.setCurrentMovie(currentMovie);
        }

        Movie selectedMovie = movieList.get(0);

        if (selectedMovie.getName().compareTo(currentMovie) == 0) {

            currentUser.getLikedMovies().add(movieList.get(0));

            selectedMovie.setNumLikes(selectedMovie.getNumLikes() + 1);

            WriteUtils.noError(movieList, currentUser);
        } else {
            WriteUtils.generalError();
        }
    }
}
