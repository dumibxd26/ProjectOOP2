import browsingoperations.Frame;
import browsingoperations.utils.BrowsingUtils;
import browsingoperations.utils.PagesUtils;
import browsingoperations.WrapperPageUtils;
import browsingoperations.utils.WriteUtils;
import initializations.ActionInfo;
import initializations.ActionsUtils;
import initializations.PageInfo;
import initializations.PageUtils;
import readinput.*;

import java.util.ArrayList;
import java.util.HashMap;
import notificationsobserver.Notifications;

public final class Run {

    private String currentPage;
    private User currentUser;
    public Run() { }

    /**
     * Function to run the program.
     * @param inputData
     */
    public void run(final Input inputData) {

        ArrayList<Action> actionsList = inputData.getActions();

        ArrayList<Movie> movieList = inputData.getMovies();
        ArrayList<Movie> notUserBannedMovies = null;
        ArrayList<Movie> filteredList = null;
        ArrayList<Frame> pagesHistory = null;
        ArrayList<User> userList = inputData.getUsers();

        Notifications notifications = new Notifications(movieList, userList);

        HashMap<User, HashMap<Movie, Integer>> userMovieRatings = new HashMap<>();

        currentUser = null;
        currentPage = "homepage neautentificat";
        filteredList = null;
        notUserBannedMovies = null;

        HashMap<String, PageInfo> pages = PageUtils.createPages();
        HashMap<String, ActionInfo> actions = ActionsUtils.createActions(movieList);
        // Iterate through te list of actions and execute each action
        for (Action actionInput : actionsList) {
            if (actionInput.getType().compareTo("change page") == 0) {

               WrapperPageUtils wrapperPageUtils = WrapperPageUtils.getInstance(currentUser,
                       currentPage, filteredList, pagesHistory);

               PagesUtils.handlePageBrowsing(pages, notUserBannedMovies,
                       actionInput.getPage(), actionInput.getMovie(),
                       wrapperPageUtils);

               currentUser = wrapperPageUtils.getCurrentUser();
               currentPage = wrapperPageUtils.getCurrentPage();
               filteredList = wrapperPageUtils.getFilteredList();

            } else if (actionInput.getType().compareTo("back") == 0) {

                if (pagesHistory == null || pagesHistory.size() < 2) {
                    WriteUtils.generalError();
                    continue;
                } else {
                    pagesHistory.remove(pagesHistory.size() - 1);
                    Frame frame = pagesHistory.get(pagesHistory.size() - 1);

                    currentPage = frame.getPageName();

                    if (frame.getFilteredList() == null) {
                        filteredList = notUserBannedMovies;
                    } else {
                        filteredList = frame.getFilteredList();
                    }

                    if (currentPage.compareTo("movies") == 0) {
                        WriteUtils.noError(notUserBannedMovies, currentUser);
                    } else if (currentPage.compareTo("see details") == 0) {
                        Movie returnMovie = frame.getSeeDetailsMovie();

                        WriteUtils.pageSeeDetails(returnMovie, currentUser);
                    }
                }
            } else if(actionInput.getType().compareTo("database") == 0) {
                    notifications.modifyState(actionInput.getFeature(),
                            actionInput.getAddedMovie(),
                            actionInput.getDeletedMovie());
                    notUserBannedMovies = BrowsingUtils
                            .filterCountry(movieList, currentUser.getCredentials().getCountry());
            } else {
                String feature = actionInput.getFeature();

                if (pages.get(currentPage).getNextActions().contains(feature)) {
                   actions.get(feature).getAction().execute(currentUser,
                           actionInput.getMovie(), movieList,
                           userList, actionInput.getStartsWith(),
                           actionInput.getCount(), actionInput.getRate(),
                           actionInput.getCredentials(), actionInput.getFilters(),
                           filteredList, notUserBannedMovies,
                           actionInput.getAddedMovie(), actionInput.getDeletedMovie()
                           ,currentPage, actions, userMovieRatings,
                           actionInput.getSubscribedGenre(), notifications);
                } else {
                   WriteUtils.generalError();
                }

                if (actions.get(feature).getAction().getActionParameters() == null) {
                   continue;
                }

                if (actions.get(feature).getAction().getActionParameters().getCurrentUser() != null) {
                   currentUser = actions.get(feature).getAction().getActionParameters().getCurrentUser();
                }

                if (actions.get(feature).getAction().getActionParameters().getFilteredList() != null) {
                   filteredList = actions.get(feature).getAction().getActionParameters().getFilteredList();
                }

                if (actions.get(feature).getAction().getActionParameters().getNotUserBannedMovies() != null) {
                   notUserBannedMovies = actions.get(feature).getAction().getActionParameters().getNotUserBannedMovies();
                }

                if (actions.get(feature).getAction().getActionParameters().getCurrentPage() != null) {
                   currentPage = actions.get(feature).getAction().getActionParameters().getCurrentPage();
                }

                if (actions.get(feature).getAction().getActionParameters().getUserList() != null) {
                    userList = actions.get(feature).getAction().getActionParameters().getUserList();
                }

                // The only action that can lead to authenticated homepage
                // is either login or register
                if (currentPage.compareTo("homepage autentificat") == 0) {
                    pagesHistory = new ArrayList<>();

                    Frame frame = new Frame("homepage autentificat", filteredList, null);
                    pagesHistory.add(frame);
                }
           }
        }

        BrowsingUtils.handlePremiumEnding(currentUser, notUserBannedMovies);
    }
}
