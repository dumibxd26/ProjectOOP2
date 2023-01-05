import browsingoperations.*;
import initializations.ActionInfo;
import initializations.ActionsUtils;
import initializations.PageInfo;
import initializations.PageUtils;
import readinput.User;
import readinput.Input;
import readinput.Movie;
import readinput.Action;

import java.util.ArrayList;
import java.util.HashMap;

public final class Run {

    private String currentPage;
    private String previousAction;
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

        previousAction = null;
        currentUser = null;
        currentPage = "homepage neautentificat";
        filteredList = null;
        notUserBannedMovies = null;

        ArrayList<User> userList = inputData.getUsers();

        HashMap<String, PageInfo> pages = PageUtils.createPages();
        HashMap<String, ActionInfo> actions = ActionsUtils.createActions(movieList);

        // Iterate through te list of actions and execute each action
        for (Action actionInput : actionsList) {

           if (actionInput.getType().compareTo("change page") == 0) {
                if (pages.get(currentPage).getNextPages().contains(actionInput.getPage())) {
                    String currentPageAux = actionInput.getPage();
                    if (currentPageAux.compareTo("logout") == 0) {
                        currentUser = null;
                        currentPage = "homepage neautentificat";
                    // Pages which need to write data
                    } else if (currentPageAux.compareTo("movies") == 0) {

                        WriteUtils.noError(notUserBannedMovies, currentUser);
                        filteredList = notUserBannedMovies;
                        currentPage = currentPageAux;
                    } else if (currentPageAux.compareTo("see details") == 0) {
                        Movie returnMovie = null;

                        for (int i = 0; i < filteredList.size(); i++) {
                            if (filteredList.get(i).getName()
                                    .compareTo(actionInput.getMovie()) == 0) {
                                 returnMovie = filteredList.get(i);
                                 break;
                            }
                        }

                        if (returnMovie != null) {
                            filteredList = WriteUtils.pageSeeDetails(returnMovie, currentUser);
                           currentPage = currentPageAux;
                        } else {
                            WriteUtils.generalError();

                            filteredList = notUserBannedMovies;
                        }

                    } else {
                        currentPage = currentPageAux;
                        filteredList = notUserBannedMovies;
                    }

                    previousAction = null;
                } else {
                    WriteUtils.generalError();
                }

           } else {
               String feature = actionInput.getFeature();

               if (pages.get(currentPage).getNextActions().contains(feature)) {
                   actions.get(feature).getAction().execute(currentUser, previousAction,
                           actionInput.getMovie(), inputData.getMovies(),
                           userList, actionInput.getStartsWith(),
                           actionInput.getCount(), actionInput.getRate(),
                           actionInput.getCredentials(), actionInput.getFilters(),
                           filteredList, notUserBannedMovies,
                           actionInput.getAddedMovie(), actionInput.getDeletedMovie()
                           ,currentPage, actions);
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

               if (actions.get(feature).getAction().getActionParameters().getPreviousAction() != null) {
                   previousAction = actions.get(feature).getAction().getActionParameters().getPreviousAction();
               }

                if (actions.get(feature).getAction().getActionParameters().getUserList() != null) {
                     userList = actions.get(feature).getAction().getActionParameters().getUserList();
                }
           }
        }
    }
}
