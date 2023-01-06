package browsingoperations;

import initializations.ObjMapper;
import readinput.Movie;
import readinput.Notification;
import readinput.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public final class WriteUtils {

    private WriteUtils() { }

    // Get the ObjMapper instance
    private static ObjMapper objMapper = ObjMapper.getInstance();

 //   private static final DecimalFormat df = new DecimalFormat("0.00"); unsued

    /**
     * Function to create the credentials node
     * @param name
     * @param password
     * @param accountType
     * @param country
     * @param balance
     * @return
     */
    public static ObjectNode createCredentials(final String name,
                                               final String password,
                                               final String accountType,
                                               final String country,
                                               final String balance) {

        ObjectNode node = objMapper.createNode();

        node.put("name", name);
        node.put("password", password);
        node.put("accountType", accountType);
        node.put("country", country);
        node.put("balance", balance);

        return node;
    }

    /**
     * Function to generate a node of the genres list
     * @param genres
     * @return
     */
    public static ArrayNode createGenresList(final ArrayList<String> genres) {

        ArrayNode arrayNode = objMapper.createArrayNode();

        for (String genre : genres) {
            arrayNode.add(genre);
        }

        return arrayNode;
    }

    /**
     * Function to generate a node of the actors list
     * @param actors
     * @return
     */
    public static ArrayNode createActorsList(final ArrayList<String> actors) {

        ArrayNode arrayNode = objMapper.createArrayNode();

        for (String actor : actors) {
            arrayNode.add(actor);
        }

        return arrayNode;
    }
    /**
     * Function to generate a node of the countries list
     * @param countries
     * @return
     */
    public static ArrayNode createCountriesList(final ArrayList<String> countries) {

        ArrayNode arrayNode = objMapper.createArrayNode();

        for (String country : countries) {
            arrayNode.add(country);
        }

        return arrayNode;
    }

    public static ArrayNode createNotificationNode(final Notification notification) {
        ArrayNode arrayNode = objMapper.createArrayNode();
        arrayNode.add(notification.getMovieName());
        arrayNode.add(notification.getMessage());
        return arrayNode;
    }

    /**
     * Function to generate a node of notifications
     * @param notifications
     * @return
     */
    public static ArrayNode createNotificationsList(final ArrayList<Notification> notifications) {

        ArrayNode arrayNode = objMapper.createArrayNode();

        for (Notification notification : notifications) {
            arrayNode.add(createNotificationNode(notification));
        }

        return arrayNode;
    }

    /**
     * Function to generate a node of A movie
     * @param movie
     * @return
     */
    public static ObjectNode createMovieNode(final Movie movie) {

        ObjectNode node = objMapper.createNode();

        node.put("name", movie.getName());
        node.put("year", movie.getYear());
        node.put("duration", movie.getDuration());
        node.put("genres", createGenresList(movie.getGenres()));
        node.put("actors", createActorsList(movie.getActors()));
        node.put("countriesBanned", createCountriesList(movie.getCountriesBanned()));
        node.put("numLikes", movie.getNumLikes());
        node.put("numRatings", movie.getNumRatings());

        if (movie.getNumRatings() != 0) {
            node.put("rating", movie.getRating() / movie.getNumRatings());
        } else {
            node.put("rating", (float) 0);
        }

        return node;
    }

    /**
     * Function to write the basic error(error: "Error", the rest null)
     */
    public static void generalError() {

        ObjectNode node = objMapper.createNode();

        node.put("error", "Error");
        node.put("currentMoviesList", objMapper.createArrayNode());
        node.put("currentUser", (String) null);

        objMapper.getOutput().add(node);
    }

    /**
     * Function to create the node for the User
     * @param user
     * @return
     */
    public static ObjectNode createUserOutput(final User user) {

        ObjectNode node = objMapper.createNode();

        node.put("credentials", createCredentials(user.getCredentials().getName(),
                user.getCredentials().getPassword(),
                user.getCredentials().getAccountType(),
                user.getCredentials().getCountry(),
                user.getCredentials().getBalance()));

        node.put("purchasedMovies", createMovieListArray(user.getPurchasedMovies()));
        node.put("watchedMovies", createMovieListArray(user.getWatchedMovies()));
        node.put("likedMovies", createMovieListArray(user.getLikedMovies()));
        node.put("ratedMovies", createMovieListArray(user.getRatedMovies()));
        node.put("tokensCount", user.getTokensCount());
        node.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
//        node.put("notifications", createNotificationsList(user.getNotifications()));

        return node;
    }

    /**
     * Function to create the output if there is no error
     * Writing the error: null
     * The current user
     * And the movie list depending on the page
     * @param currentMoviesList
     * @param user
     */
    public static void noError(final ArrayList<Movie> currentMoviesList, final  User user) {

        ObjectNode node = objMapper.createNode();

        node.put("error", (String) null);
        node.put("currentMoviesList", createMovieListArray(currentMoviesList));
        node.put("currentUser", createUserOutput(user));

        objMapper.getOutput().add(node);
    }

    /**
     * Function to create a node of a movie list
     * @param movieList
     * @return
     */
    public static ArrayNode createMovieListArray(final ArrayList<Movie> movieList) {
        ArrayNode arrayNode = objMapper.createArrayNode();

        if (movieList == null) {
            return arrayNode;
        }

        for (Movie movie : movieList) {
            arrayNode.add(createMovieNode(movie));
        }

        return arrayNode;
    }

    /**
     * Login output handler
     * @param user
     */
    public static void userLogin(final User user) {

        ObjectNode node = objMapper.createNode();

        noError(null, user);
    }

    /**
     * Register output handler
     * @param user
     */
    public static void userRegister(final User user) {

        ObjectNode node = objMapper.createNode();

        noError(null, user);
    }

    /**
     * Page see details handler
     * @param currentMovie
     * @param currentUser
     * @return
     */
    public static ArrayList<Movie> pageSeeDetails(final Movie currentMovie,
                                                    final User currentUser) {
        ObjectNode node = objMapper.createNode();
        ArrayNode arrayNode = objMapper.createArrayNode();

        ArrayList<Movie> oneMovieList = new ArrayList<Movie>();

        oneMovieList.add(currentMovie);

        noError(oneMovieList, currentUser);

        return oneMovieList;
    }

}
