package readinput;

import java.util.ArrayList;

public final class Contains {

    private ArrayList<String> actors;
    private ArrayList<String> genres;

    // your error
    private ArrayList<String> genre;

    private Contains() { }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
