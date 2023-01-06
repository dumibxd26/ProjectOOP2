package notificationsobserver;

import browsingoperations.utils.WriteUtils;
import readinput.Movie;
import readinput.Notification;
import readinput.User;

import java.util.ArrayList;

//public interface Subject {
//    void registerObserver(Observer o);
//    void notifyObservers();
//}
//
//public interface Observer {
//    void update(Subject s);
//}

public class Notifications {
    private ArrayList<User> observers;

    ArrayList<Movie> movieList;

    // initialise object
    public Notifications(ArrayList<Movie> movieList,
                                  ArrayList<User> userList) {
        this.movieList = movieList;
        observers = userList;
    }

    public void registerObserver(User newUser) {
        observers.add(newUser);
    }

    public void modifyState(String actionType, Movie movie, String deletedMovie) {

        // Notify all observers
        if (actionType.compareTo("add") == 0) {
            handleAdd(movie);
        } else if (actionType.compareTo("delete") == 0) {
            handleRemove(deletedMovie);
        }
    }

    private void handleAdd(Movie movie) {

        // check if the movie list contains the movie
        if (movieList.contains(movie)) {
            WriteUtils.generalError();
            return ;
        }

         for (User user : observers) {

            for (String genre : movie.getGenres()) {
                if(user.getSubscribedGenres().contains(genre)) {
                    Notification notification = new Notification(movie.getName(), "ADD");
                    user.getNotifications().add(notification);
                    break;
                }
            }
        }
        movieList.add(movie);
    }

    private void handleRemove(String deletedMovieName) {

        Movie deleteMovie = null;

        for (Movie movie : movieList) {
            if (movie.getName().compareTo(deletedMovieName) == 0) {

                Movie deletedMovie = movie;
                for (User user : observers) {

                        if (user.getPurchasedMovies().contains(deletedMovie)) {
                            Notification notification = new Notification(deletedMovie.getName(), "DELETE");
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


