package browsingoperations;

import readinput.Movie;

import java.util.ArrayList;

public class Frame {

    private String pageName;
    private ArrayList<Movie> filteredList;
    private Movie seeDetailsMovie;

    public Frame(final String pageName,
                 final ArrayList<Movie> filteredList,
                 final Movie seeDetailsMovie) {
        this.pageName = pageName;
        this.filteredList = filteredList;
        this.seeDetailsMovie = seeDetailsMovie;
    }

    /**
     * getter for frame page name
     * @return the pageName
     */
    public String getPageName() {
        return pageName;
    }
    /**
     * getter for frame filtered list
     * @return the filteredList
     */
    public ArrayList<Movie> getFilteredList() {
        return filteredList;
    }

    /**
     * getter for frame see details page movie
     * @return the seeDetailsMovie
     */
    public Movie getSeeDetailsMovie() {
        return seeDetailsMovie;
    }
}
