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


    //ObjectMapper mapper = new ObjectMapper();
    private String currentPage;
    private String previousAction;
    private User currentUser;

    public Run() { }

    /**
     * Function to run the program.
     * @param inputData
     */
    public void run(final Input inputData) {

        // Initialise all Singleton Classes for each tests
        Login.setInstance();
        Register.setInstance();
        Like.setInstance();
        RateTheMovie.setInstance();
        BuyPremiumAccount.setInstance();
        BuyTokens.setInstance();
        Filter.setInstance();
        Search.setInstance();
        Like.setInstance();
        Purchase.setInstance();
        Watch.setInstance();

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

                ActionBuilder currentAction;

               if (feature.compareTo("login") == 0) {
                   if (pages.get(currentPage).getNextActions().contains("login")) {

                       movieList = BrowsingUtils.newArr(inputData.getMovies());

                       currentAction = Login.getInstance(movieList, userList, currentUser,
                               actionInput.getCredentials());

                       currentAction.executeAction();
                       currentUser = currentAction.getCurrentUser();
                       if (currentUser != null) {

                           filteredList = notUserBannedMovies = currentAction.getMovieList();
                           currentPage = "homepage autentificat";

                           previousAction = "login";
                       }
                   } else {
                       WriteUtils.generalError();
                   }

               } else if (feature.compareTo("register") == 0) {

                   if (pages.get(currentPage).getNextActions().contains("register")) {

                       movieList = BrowsingUtils.newArr(inputData.getMovies());
                       currentAction = Register.getInstance(movieList, userList,
                               actionInput.getCredentials());

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();
                       userList = currentAction.getUserList();

                       if (currentUser != null) {
                           currentPage = "homepage autentificat";
                           filteredList = notUserBannedMovies = currentAction.getMovieList();

                           previousAction = "register";
                       }
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("search") == 0) {

                   if (pages.get(currentPage).getNextActions().contains("search")) {
                       currentAction = Search.getInstance(notUserBannedMovies, currentUser,
                               actionInput.getStartsWith());

                       filteredList = currentAction.getMovieList();

                       currentAction.executeAction();
                       previousAction = "search";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("filter") == 0) {
                   if (pages.get(currentPage).getNextActions().contains("filter")) {

                       movieList = BrowsingUtils.newArr(notUserBannedMovies);
                       currentAction = Filter.getInstance(movieList, currentUser,
                               actionInput.getFilters());

                       currentAction.executeAction();

                       filteredList = currentAction.getMovieList();
                       previousAction = "filter";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("purchase") == 0) {

                   if (pages.get(currentPage).getNextActions().contains("purchase")) {

                       if (actionInput.getMovie() != null) {
                           currentAction = Purchase.getInstance(filteredList, currentUser,
                                   actionInput.getMovie());
                       } else {
                           currentAction = Purchase.getInstance(filteredList, currentUser,
                                   filteredList.get(0).getName());
                       }

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();

                       previousAction = "purchase";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("buy tokens") == 0) {

                   if (pages.get(currentPage).getNextActions().contains("buy tokens")) {
                       currentAction = BuyTokens.getInstance(currentUser,
                               Integer.parseInt(actionInput.getCount()));

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();

                       previousAction = "buy tokens";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("buy premium account") == 0) {

                   if (pages.get(currentPage).getNextActions().contains("buy premium account")) {
                       currentAction = BuyPremiumAccount.getInstance(currentUser);

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();

                       previousAction = "buy premium account";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("watch") == 0) {
                    // Check if the movie was purchased first
                   if (pages.get(currentPage).getNextActions().contains("watch")
                           && previousAction != null
                           && actions.get(previousAction).getNextActions().contains("watch")) {

                       if (actionInput.getMovie() != null) {
                           currentAction = Watch.getInstance(filteredList, currentUser,
                                   actionInput.getMovie());
                       } else {
                           currentAction = Watch.getInstance(filteredList, currentUser,
                                   filteredList.get(0).getName());
                       }

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();

                       previousAction = "watch";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("like") == 0) {
                    // Check if the movie was watched first
                   if (pages.get(currentPage).getNextActions().contains("like")
                           && previousAction != null
                           && actions.get(previousAction).getNextActions().contains("like")) {

                       if (actionInput.getMovie() != null) {
                           currentAction = Like.getInstance(filteredList, currentUser,
                                   actionInput.getMovie());
                       } else {
                           currentAction = Like.getInstance(filteredList, currentUser,
                                   filteredList.get(0).getName());
                       }

                       currentAction.executeAction();

                       currentUser = currentAction.getCurrentUser();
                       previousAction = "like";
                   } else {
                       WriteUtils.generalError();
                   }
               } else if (feature.compareTo("rate") == 0) {
                    // check if the movie was watched first
                   if (pages.get(currentPage).getNextActions().contains("rate the movie")
                           && previousAction != null
                           && actions.get(previousAction).getNextActions()
                           .contains("rate the movie")) {

                       if (actionInput.getMovie() != null) {
                           currentAction = RateTheMovie.getInstance(filteredList, currentUser,
                                   actionInput.getMovie(),
                                   Integer.parseInt(actionInput.getRate()));
                       } else {
                           currentAction = RateTheMovie.getInstance(filteredList, currentUser,
                                   filteredList.get(0).getName(),
                                   Integer.parseInt(actionInput.getRate()));
                       }

                       currentAction.executeAction();
                       currentUser = currentAction.getCurrentUser();

                       previousAction = "rate the movie";
                   } else {
                       WriteUtils.generalError();

                   }
               }
           }
        }
    }
}
