package browsingoperations;

import readinput.Movie;

import java.util.ArrayList;

public class Frame {

    String pageName;
    ArrayList<Movie> filteredList;
    Movie seeDetailsMovie;

    public Frame(String pageName, ArrayList<Movie> filteredList, Movie seeDetailsMovie) {
        this.pageName = pageName;
        this.filteredList = filteredList;
        this.seeDetailsMovie = seeDetailsMovie;
    }

    public String getPageName() {
        return pageName;
    }

    public ArrayList<Movie> getFilteredList() {
        return filteredList;
    }

    public Movie getSeeDetailsMovie() {
        return seeDetailsMovie;
    }
}
