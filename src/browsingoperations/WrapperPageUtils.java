package browsingoperations;

import readinput.Movie;
import readinput.User;

import java.util.ArrayList;

public final class WrapperPageUtils {

    private static User currentUser;
    private static String currentPage;
    private static ArrayList<Movie> filteredList;
    private static ArrayList<Frame> pagesHistory;

    private static WrapperPageUtils instance = null;

    private WrapperPageUtils(final User currentUser,
                             final String currentPage,
                             final ArrayList<Movie> filteredList,
                             final ArrayList<Frame> pagesHistory) {
        this.currentUser = currentUser;
        this.currentPage = currentPage;
        this.filteredList = filteredList;
        this.pagesHistory = pagesHistory;
    }

    /** singleton pattern
     * this class is basically a helper for
     * change page operations
     * @return the instance
     */
    public static WrapperPageUtils getInstance(final User currentUserParam,
                                               final String currentPageParam,
                                               final ArrayList<Movie> filteredListParam,
                                               final ArrayList<Frame> pagesHistoryParam) {
        if (instance == null) {
            instance = new WrapperPageUtils(currentUserParam, currentPageParam,
                                            filteredListParam, pagesHistoryParam);
        }

        currentUser = currentUserParam;
        currentPage = currentPageParam;
        filteredList = filteredListParam;
        pagesHistory = pagesHistoryParam;

        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Movie> getFilteredList() {
        return filteredList;
    }

    public void setFilteredList(final ArrayList<Movie> filteredList) {
        this.filteredList = filteredList;
    }

   public ArrayList<Frame> getPagesHistory() {
        return pagesHistory;
    }

    public void setPagesHistory(final ArrayList<Frame> pagesHistory) {
        this.pagesHistory = pagesHistory;
    }
}
