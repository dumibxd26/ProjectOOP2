package browsingoperations;

import readinput.Credentials;
import readinput.Movie;
import readinput.User;
import readinput.Filters;
import java.util.ArrayList;

public class ActionBuilder {
    private String name;
    private User currentUser;
    private String previousAction;
    private String currentMovie;
    private ArrayList<Movie> movieList;
    private ArrayList<User> userList;
    private String startsWith;
    private int count;
    private int rate;
    private Credentials credentials;
    private Filters filters;

    public ActionBuilder() { }

    public static final class Builder {

        private final String name;
        private User currentUser;
        private ArrayList<Movie> movieList;
        private ArrayList<User> userList;

        private String previousAction = null;
        private String currentMovie = null;
        private String startsWith;
        private int count;
        private int rate;
        private Credentials credentials;
        private Filters filters;

        public Builder(final String name) {
            this.name = name;
        }

        /**
         * Builder function for movieList
         * @param movieListParam
         * @return
         */
        public Builder movieList(final ArrayList<Movie> movieListParam) {
            this.movieList = movieListParam;
            return this;
        }

        /**
         * Builder function for userList
         * @param userListParam
         * @return
         */
        public Builder userList(final ArrayList<User> userListParam) {
            this.userList = userListParam;
            return this;
        }
        /**
         * Builder function for currentUser
         * @param currentUserParam
         * @return
         */
        public Builder currentUser(final User currentUserParam) {
            this.currentUser = currentUserParam;
            return this;
        }
        /**
         * Builder function for previousAction
         * @param previousActionParam
         * @return
         */
        public Builder previousAction(final String previousActionParam) {
            this.previousAction = previousActionParam;
            return this;
        }

        /**
         * Builder function for currentMovie
         * @param currentMovieParam
         * @return
         */
        public Builder currentMovie(final String currentMovieParam) {
            this.currentMovie = currentMovieParam;
            return this;
        }
        /**
         * Builder function for startsWith
         * @param startsWithParam
         * @return
         */
        public Builder startsWith(final String startsWithParam) {
            this.startsWith = startsWithParam;
            return this;
        }
        /**
         * Builder function for count
         * @param countParam
         * @return
         */
        public Builder count(final int countParam) {
            this.count = countParam;
            return this;
        }
        /**
         * Builder function for rate
         * @param rateParam
         * @return
         */
        public Builder rate(final int rateParam) {
            this.rate = rateParam;
            return this;
        }
        /**
         * Builder function for credentials
         * @param credentialsParam
         * @return
         */
        public Builder credentials(final Credentials credentialsParam) {
            this.credentials = credentialsParam;
            return this;
        }
        /**
         * Builder function for filters
         * @param filtersParam
         * @return
         */
        public Builder filters(final Filters filtersParam) {
            this.filters = filtersParam;
            return this;
        }
        /**
         * build function
         * @return
         */
        public ActionBuilder build() {
            return new ActionBuilder(this);
        }
    }

    private ActionBuilder(Builder builder) {
        this.name = builder.name;
        this.currentUser = builder.currentUser;
        this.previousAction = builder.previousAction;
        this.currentMovie = builder.currentMovie;
        this.movieList = builder.movieList;
        this.userList = builder.userList;
        this.startsWith = builder.startsWith;
        this.count = builder.count;
        this.rate = builder.rate;
        this.credentials = builder.credentials;
        this.filters = builder.filters;
    }

    /**
     * Getter for name
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for currentUser
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for currentUser
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Getter for previousAction
     * @return
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * Getter for previousAction
     * @return
     */
    public String getPreviousAction() {
        return previousAction;
    }
    /**
     * Setter for currentMovie
     * @return
     */
    public void setPreviousAction(String previousAction) {
        this.previousAction = previousAction;
    }
    /**
     * Getter for currentMovie
     * @return
     */
    public String getCurrentMovie() {
        return currentMovie;
    }
    /**
     * Setter for movieList
     * @return
     */
    public void setCurrentMovie(String currentMovie) {
        this.currentMovie = currentMovie;
    }
    /**
     * Getter for movieList
     * @return
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }
    /**
     * Setter for userList
     * @return
     */
    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
    /**
     * Getter for userList
     * @return
     */
    public ArrayList<User> getUserList() {
        return userList;
    }
    /**
     * Setter for startsWith
     * @return
     */
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    /**
     * Getter for startsWith
     * @return
     */
    public String getStartsWith() {
        return startsWith;
    }
    /**
     * Setter for count
     * @return
     */
    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }
    /**
     * Getter for count
     * @return
     */
    public int getCount() {
        return count;
    }
    /**
     * Setter for rate
     * @return
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * Getter for rate
     * @return
     */
    public int getRate() {
        return rate;
    }
    /**
     * Setter for credentials
     * @return
     */
    public void setRate(int rate) {
        this.rate = rate;
    }
    /**
     * Getter for credentials
     * @return
     */
    public Credentials getCredentials() {
        return credentials;
    }
    /**
     * Setter for filters
     * @return
     */
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    /**
     * Getter for filters
     * @return
     */
    public Filters getFilters() {
        return filters;
    }
    /**
     * Setter for filters
     * @return
     */
    public void setFilters(Filters filters) {
        this.filters = filters;
    }
    /**
     * Inheritence function
     */
    public void executeAction() { }

}
