package notificationsobserver;

import browsingoperations.utils.WriteUtils;
import readinput.Movie;
import readinput.Notification;
import readinput.User;

import java.util.ArrayList;

public class Notifications {
    private ArrayList<User> observers;

    private ArrayList<Movie> movieList;

    // initialise object
    public Notifications(final ArrayList<Movie> movieList,
                         final ArrayList<User> userList) {
        this.movieList = movieList;
        observers = userList;
    }

    /**
     * Add a new observer(a user)
     * @param newUser
     */
    public void registerObserver(final User newUser) {
        observers.add(newUser);
    }

    /**
     * A new user is added(a new user registers)
     * @param actionType
     * @param movie
     * @param deletedMovie
     */
    public void modifyState(final String actionType,
                            final Movie movie,
                            final String deletedMovie) {

        // Notify all observers
        if (actionType.compareTo("add") == 0) {
            handleAdd(movie);
        } else if (actionType.compareTo("delete") == 0) {
            handleDelete(deletedMovie);
        }
    }

    /**
     * notify all observers
     * if a movie is added and it is not banned for their country
     * and it is not already in the list of movies
     * @param movie
     */
    private void handleAdd(final Movie movie) {

        // check if the movie list contains the movie
        for (Movie listMovie : movieList) {
            if (listMovie.getName().compareTo(movie.getName()) == 0) {
                WriteUtils.generalError();
                return;
            }
        }

         for (User user : observers) {

            for (String genre : movie.getGenres()) {
                if (user.getSubscribedGenres().contains(genre)
                    && !movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {

                    Notification notification = new Notification(movie.getName(), "ADD");
                    user.getNotifications().add(notification);
                    break;
                }
            }
        }
        movieList.add(movie);
    }

    /**
     * notify all observers
     * if a movie is deleted and they had purchased it
     * and handle the case if a user is premium or not
     * @param deletedMovieName
     */
    private void handleDelete(final String deletedMovieName) {

        Movie deleteMovie = null;

        for (Movie movie : movieList) {
            if (movie.getName().compareTo(deletedMovieName) == 0) {

                Movie deletedMovie = movie;
                for (User user : observers) {

                        if (user.getPurchasedMovies().contains(deletedMovie)) {
                            Notification notification = new Notification(deletedMovie.getName(),
                                    "DELETE");
                            user.getNotifications().add(notification);

                            // delete the movie from the user's purchased movies
                            user.getPurchasedMovies().remove(deletedMovie);

                            // delete the movie from the user's watched movies
                            if (user.getWatchedMovies().contains(deletedMovie)) {
                                user.getWatchedMovies().remove(deletedMovie);
                            }
                            // delete the movie from the user's liked movies
                            if (user.getLikedMovies().contains(deletedMovie)) {
                                user.getLikedMovies().remove(deletedMovie);
                            }
                            // delete the movie from the user's rated movies
                            if (user.getRatedMovies().contains(deletedMovie)) {
                                user.getRatedMovies().remove(deletedMovie);
                            }

                            if (user.getCredentials().getAccountType().compareTo("premium") == 0) {
                                user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                            } else {
                                user.setTokensCount(user.getTokensCount() + 2);
                            }
                        }
                }
                deleteMovie = movie;
                break;
            }
        }

        if (deleteMovie == null) {
            WriteUtils.generalError();
        } else {
            movieList.remove(deleteMovie);
        }
    }
}


