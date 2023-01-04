package readinput;

import java.util.ArrayList;

public final class Movie {

    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private int numLikes = 0;
    private float rating = 0;
    private int numRatings = 0;

    public Movie() { }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public float getRating() {
        return rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public void setRating(final float rating) {
        this.rating = rating;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }
}
