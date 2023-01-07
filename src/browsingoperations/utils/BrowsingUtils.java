package browsingoperations.utils;

import readinput.Movie;
import readinput.Notification;
import readinput.User;
import readinput.Credentials;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.KeyPair;
import java.util.*;

public final class BrowsingUtils {

    private BrowsingUtils() { }

    /**
     * Checks if the user exists in the database
     * @param userList
     * @param credentials
     * @return
     */
    public static User checkUserExistence(final ArrayList<User> userList,
                                          final Credentials credentials) {
        // make a stream with reduce

        User user = userList.stream()
                .filter(u -> u.getCredentials().getName().compareTo(credentials.getName()) == 0
                        && u.getCredentials().getPassword().compareTo(credentials.getPassword())
                        == 0)
                .reduce(null, (u1, u2) -> u2);

        return user;
    }
    /**
     * Filters the movies that start with the given title
     * @param movieList
     * @param title
     * @return
     */
    public static ArrayList<Movie> filterStartsWith(final ArrayList<Movie> movieList,
                                                    final String title) {
        ArrayList<Movie> filteredList = new ArrayList<>();

        for (Movie movie : movieList) {
            if (movie.getName().startsWith(title)) {
                filteredList.add(movie);
            }
        }
        return filteredList;
    }
    /**
     * Filters the movies that contain the given actors and genres
     * @param movieList
     * @param actors
     * @param genres
     * @return
     */
    public static ArrayList<Movie> filterContains(final ArrayList<Movie> movieList,
                                                  final ArrayList<String> actors,
                                                  final ArrayList<String> genres) {

        ArrayList<Movie> filteredList = new ArrayList<>();

        for (Movie movie : movieList) {
            int foundActor = 1;
            int foundGenre = 1;

            if (actors != null) {
                for (String actor : actors) {
                    int oneActorFound = 0;

                    for (String movieActor: movie.getActors()) {
                        if (actor.compareTo(movieActor) == 0) {
                            oneActorFound = 1;
                            break;
                        }
                    }

                    if (oneActorFound != 1) {
                        foundActor = 0;
                        break;
                    }
                }
            }

            if (genres != null) {

                for (String genre : genres) {
                    int foundOneGenre = 0;

                    for (String movieGenre: movie.getGenres()) {
                        if (genre.compareTo(movieGenre) == 0) {
                            foundOneGenre = 1;
                            break;
                        }
                    }

                    if (foundOneGenre != 1) {
                        foundGenre = 0;
                        break;
                    }
                }
            }

            if (foundActor == 1 && foundGenre == 1) {
                filteredList.add(movie);
            }
        }
        return filteredList;
    }

    /**
     * Sort function by rating
     * @param movieList
     * @param order
     * @return
     */
    public static ArrayList<Movie> sortByRating(final ArrayList<Movie> movieList,
                                                final String order) {

        if (order.compareTo("increasing") == 0) {
            movieList.sort((m1, m2) -> Float.compare(m1.getRating(), m2.getRating()));
        } else {
            movieList.sort((m1, m2) -> Float.compare(m2.getRating(), m1.getRating()));
        }

        return movieList;
    }
    /**
     * Sort function by duration
     * @param movieList
     * @param order
     * @return
     */
    public static ArrayList<Movie> sortByDuration(final ArrayList<Movie> movieList,
                                                  final String order) {

        if (order.compareTo("increasing") == 0) {
            movieList.sort((m1, m2) -> Integer.compare(m1.getDuration(), m2.getDuration()));
        } else {
            movieList.sort((m1, m2) -> Integer.compare(m2.getDuration(), m1.getDuration()));
        }

        return movieList;
    }

    /**
     * Sort function by both rating and duration
     * @param movieList
     * @param orderRating
     * @param orderDuration
     * @return
     */
    public static ArrayList<Movie> sortByBoth(final ArrayList<Movie> movieList,
                                              final String orderRating,
                                              final String orderDuration) {

        if (orderRating.compareTo("increasing") == 0
                && orderDuration.compareTo("increasing") == 0) {

            movieList.sort((m1, m2) -> {
                if (m1.getDuration() == m2.getDuration()) {
                    return Float.compare(m1.getNumRatings() == 0 ? 0
                                    : m1.getRating() / m1.getNumRatings(),
                            m2.getNumRatings() == 0 ? 0 : m2.getRating() / m2.getNumRatings());
                } else {
                    return Integer.compare(m1.getDuration(), m2.getDuration());
                }
            });
        }
        if (orderRating.compareTo("increasing") == 0
                && orderDuration.compareTo("decreasing") == 0) {

            movieList.sort((m1, m2) -> {
                if (m1.getDuration() == m2.getDuration()) {

                    return Float.compare(m1.getNumRatings() == 0 ? 0
                                    : m1.getRating() / m1.getNumRatings(),
                            m2.getNumRatings() == 0 ? 0 : m2.getRating() / m2.getNumRatings());
                } else {
                    return Integer.compare(m2.getDuration(), m1.getDuration());
                }
            });
        }

        if (orderRating.compareTo("decreasing") == 0
                && orderDuration.compareTo("increasing") == 0) {

            movieList.sort((m1, m2) -> {
                if (m1.getDuration() == m2.getDuration()) {
                    return Float.compare(m2.getNumRatings() == 0 ? 0
                                    : m2.getRating() / m2.getNumRatings(),
                            m1.getNumRatings() == 0 ? 0 : m1.getRating() / m1.getNumRatings());
                } else {
                    return Integer.compare(m1.getDuration(), m2.getDuration());
                }
            });
        }

        if (orderRating.compareTo("decreasing") == 0
                && orderDuration.compareTo("decreasing") == 0) {

            movieList.sort((m1, m2) -> {
                if (m1.getDuration() == m2.getDuration()) {
                    return Float.compare(m2.getNumRatings() == 0 ? 0
                                    : m2.getRating() / m2.getNumRatings(),
                            m1.getNumRatings() == 0 ? 0 : m1.getRating() / m1.getNumRatings());
                } else {
                    return Integer.compare(m2.getDuration(), m1.getDuration());
                }
            });
        }

        return movieList;
    }

    /**
     * filter function by banned countries
     * @param movieList
     * @param bannedCountry
     * @return
     */
    public static ArrayList<Movie> filterCountry(final ArrayList<Movie> movieList,
                                                 final String bannedCountry) {
        ArrayList<Movie> filteredList = new ArrayList<Movie>();

        for (Movie movie : movieList) {
            // array.contains -> not good
            int ok = 1;
            for (String country : movie.getCountriesBanned()) {
                if (country.compareTo(bannedCountry) == 0) {
                    ok = 0;
                    break;
                }
            }

            if (ok == 1) {
                filteredList.add(movie);
            }
        }
        return filteredList;
    }

    /**
     * Utility function to create a new array with the same elements as the input array
     * @param movieList
     * @return
     */
    public static ArrayList<Movie> newArr(final ArrayList<Movie> movieList) {
        ArrayList<Movie> newArr = new ArrayList<Movie>();

        movieList.stream()
                .forEach(m -> newArr.add(m));

        return newArr;
    }

    public static void handlePremiumEnding(final User user, final ArrayList<Movie> movieList) {


        if (user == null || user.getCredentials().getAccountType().compareTo("standard") == 0) {
            return;
        }

        ArrayList<Movie> likedMovies = user.getLikedMovies();

        HashMap<String, Integer> mapGenres = new HashMap<String, Integer>();

        for (Movie movie : likedMovies) {
            for (String genre : movie.getGenres()) {
                if (mapGenres.containsKey(genre)) {
                    mapGenres.put(genre, mapGenres.get(genre) + 1);
                } else {
                    mapGenres.put(genre, 1);
                }
            }
        }
       class Pair {
            String genre;
            int likes;

            public Pair(String genre, int likes) {
                this.genre = genre;
                this.likes = likes;
            }

           public String getGenre() {
               return genre;
           }
           public int getLikes() {
               return likes;
           }
       }

        ArrayList<Pair> genresArray = new ArrayList<Pair>();

        for (Map.Entry<String, Integer> entry : mapGenres.entrySet()) {
            genresArray.add(new Pair(entry.getKey(), entry.getValue()));
        }

        // sorting by likes in decreasing order, and if the likes are equal
        // sort by genre in increasing order
        // using comparator.comparing!!

        genresArray.sort(Comparator.comparing(Pair::getLikes).reversed()
                .thenComparing(Pair::getGenre));

        // sort the movies in decreasing order by the number of likes using comparator.comparing!!

        movieList.sort(Comparator.comparing(Movie::getNumLikes).reversed());

        String movieName = "No recommendation";

        boolean foundBest = false;
        for (Pair movieParams : genresArray) {
            for (Movie movie : movieList) {
                if (movie.getGenres().contains(movieParams.getGenre())) {

                    boolean found = false;
                    for (Movie movieCmp : user.getWatchedMovies()) {
                        if (movieCmp.getName().compareTo(movie.getName()) == 0) {
                            found = true;
                            break;
                        }
                    }

                    if (found == false) {
                        movieName = movie.getName();
                        foundBest = true;
                        break;
                    }
                }
            }
            if (foundBest == true) {
                break;
            }
        }

        Notification notification = new Notification(movieName, "Recommendation");

        user.getNotifications().add(notification);
        WriteUtils.finalPremium(user);

    }
}
