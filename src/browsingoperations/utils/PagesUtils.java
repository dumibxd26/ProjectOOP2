package browsingoperations.utils;

import browsingoperations.Frame;
import browsingoperations.WrapperPageUtils;
import initializations.PageInfo;
import readinput.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public final class PagesUtils {

    private PagesUtils() { }
    public static void handlePageBrowsing(final HashMap<String, PageInfo> pages,
                                          final ArrayList<Movie> notUserBannedMovies,
                                          final String actionPage,
                                          final String actionMovie,
                                          final WrapperPageUtils wrapperPageUtils) {

        if (pages.get(wrapperPageUtils.getCurrentPage()).getNextPages().contains(actionPage)) {
            String currentPageAux = actionPage;
            if (currentPageAux.compareTo("logout") == 0) {
                wrapperPageUtils.setCurrentUser(null);
                wrapperPageUtils.setCurrentPage("homepage neautentificat");
                wrapperPageUtils.setPagesHistory(null);
                // Pages which need to write data
            } else if (currentPageAux.compareTo("movies") == 0) {

                WriteUtils.noError(notUserBannedMovies, wrapperPageUtils.getCurrentUser());
                wrapperPageUtils.setFilteredList(notUserBannedMovies);
                wrapperPageUtils.setCurrentPage(currentPageAux);

                Frame frame = new Frame(wrapperPageUtils.getCurrentPage(),
                        BrowsingUtils.newArr(notUserBannedMovies),
                        null);

                wrapperPageUtils.getPagesHistory().add(frame);

            } else if (currentPageAux.compareTo("see details") == 0) {
                Movie returnMovie = null;

                for (int i = 0; i < wrapperPageUtils.getFilteredList().size(); i++) {
                    if (wrapperPageUtils.getFilteredList().get(i).getName()
                            .compareTo(actionMovie) == 0) {
                        returnMovie = wrapperPageUtils.getFilteredList().get(i);
                        break;
                    }
                }

                if (returnMovie != null) {
                    wrapperPageUtils.setFilteredList(WriteUtils.pageSeeDetails(returnMovie,
                            wrapperPageUtils.getCurrentUser()));
                    wrapperPageUtils.setCurrentPage(currentPageAux);

                    Frame frame = new Frame(wrapperPageUtils.getCurrentPage(),
                            BrowsingUtils.newArr(wrapperPageUtils.getFilteredList()),
                            returnMovie);

                    wrapperPageUtils.getPagesHistory().add(frame);
                } else {
                    WriteUtils.generalError();

                    wrapperPageUtils.setFilteredList(notUserBannedMovies);
                }

            } else {
                wrapperPageUtils.setCurrentPage(currentPageAux);
                wrapperPageUtils.setFilteredList(notUserBannedMovies);

                if (wrapperPageUtils.getPagesHistory() != null) {
                    Frame frame = new Frame(wrapperPageUtils.getCurrentPage(),
                            BrowsingUtils.newArr(notUserBannedMovies),
                            null);

                    wrapperPageUtils.getPagesHistory().add(frame);
                }
            }

        } else {
            WriteUtils.generalError();
        }

    }
}
