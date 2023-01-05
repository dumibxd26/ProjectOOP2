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
    private String currentPage;
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> filteredList;
    private ArrayList<Movie> notUserBannedMovies;
    private ArrayList<User> userList;

    public ActionBuilder() { }

    public static final class Builder {

        private final String name;
        private User currentUser = null;
        private ArrayList<Movie> movieList;
        private ArrayList<Movie> filteredList = null;
        private ArrayList<Movie> notUserBannedMovies = null;
        private ArrayList<User> userList = null;
        private String previousAction = null;
        private String currentPage = null;
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
         * Builder function for previousAction
         * @param previousActionParam
         * @return
         */
        public Builder previousAction(final String previousActionParam) {
            this.previousAction = previousActionParam;
            return this;
        }
        /**
         * Builder function for filteredList
         * @param filteredListParam
         * @return
         */
        public Builder filteredList(final ArrayList<Movie> filteredListParam) {
            this.filteredList = filteredListParam;
            return this;
        }
        /**
         * Builder function for notUserBannedMovies
         * @param notUserBannedMoviesParam
         * @return
         */
        public Builder notUserBannedMovies(final ArrayList<Movie> notUserBannedMoviesParam) {
            this.notUserBannedMovies = notUserBannedMoviesParam;
            return this;
        }
        /**
         * Builder function for currentPage
         * @param currentPageParam
         * @return
         */
        public Builder currentPage(final String currentPageParam) {
            this.currentPage = currentPageParam;
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
         * build function
         * @return
         */
        public ActionBuilder build() {
            return new ActionBuilder(this);
        }
    }

    private ActionBuilder(Builder builder) {
        this.name = builder.name;
        this.previousAction = builder.previousAction;
        this.movieList = builder.movieList;
        this.userList = builder.userList;
        this.filteredList = builder.filteredList;
        this.notUserBannedMovies = builder.notUserBannedMovies;
        this.currentPage = builder.currentPage;
        this.currentUser = builder.currentUser;
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

    public ArrayList<Movie> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(ArrayList<Movie> filteredList) {
        this.filteredList = filteredList;
    }

    public ArrayList<Movie> getNotUserBannedMovies() {
        return notUserBannedMovies;
    }

    public void setNotUserBannedMovies(ArrayList<Movie> notUserBannedMovies) {
        this.notUserBannedMovies = notUserBannedMovies;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

}
