package browsingoperations;

import readinput.Movie;
import readinput.User;
import java.util.ArrayList;

public class ActionBuilder {
    private String name;
    private User currentUser;
    private String currentPage;
    private ArrayList<Movie> filteredList;
    private ArrayList<Movie> notUserBannedMovies;

    public ActionBuilder() { }

    public static final class Builder {

        private final String name;
        private User currentUser = null;
        private ArrayList<Movie> filteredList = null;
        private ArrayList<Movie> notUserBannedMovies = null;
        private String currentPage = null;
        public Builder(final String name) {
            this.name = name;
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

    private ActionBuilder(final Builder builder) {
        this.name = builder.name;
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
    public void setName(final String name) {
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
     * setter for currentUser
     * @param currentUser
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * Getter for filteredList
     * @return filteredList
     */
    public ArrayList<Movie> getFilteredList() {
        return filteredList;
    }
    /**
     * Setter for filteredList
     * @param filteredList
     */
    public void setFilteredList(final ArrayList<Movie> filteredList) {
        this.filteredList = filteredList;
    }
    /**
     * Getter for notUserBannedMovies
     * @return notUserBannedMovies
     */
    public ArrayList<Movie> getNotUserBannedMovies() {
        return notUserBannedMovies;
    }
    /**
     * Setter for notUserBannedMovies
     * @param notUserBannedMovies
     */
    public void setNotUserBannedMovies(final ArrayList<Movie> notUserBannedMovies) {
        this.notUserBannedMovies = notUserBannedMovies;
    }
    /**
     * Getter for currentPage
     * @return currentPage
     */
    public String getCurrentPage() {
        return currentPage;
    }
    /**
     * Setter for currentPage
     * @param currentPage
     */
    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

}
