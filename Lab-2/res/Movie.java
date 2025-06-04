package ense375;
import java.util.List;
/**
 * The {@code Movie} class represents a movie with a title, a list of genres, and a release year.
 * It provides methods to access and modify these attributes.
 * 
 * This class is useful for applications that need to manage or categorize movies by metadata.
 * 
 * Example usage:
 * <pre>
 *     Movie movie = new Movie("Inception", Arrays.asList("Action", "Sci-Fi", "Thriller"), 2010);
 * </pre>
 * 
 * @author  Trevor Douglas
 * @version 1.0
 */
public class Movie {
    
    /** The title of the movie. */
    private String title;
    
    /** The list of genres associated with the movie. */
    private List<String> genres;
    
    /** The year the movie was released. */
    private int year;

    /**
     * Constructs a new {@code Movie} with the specified title, genres, and release year.
     * 
     * @param title  the title of the movie
     * @param genres the list of genres
     * @param year   the release year
     */
    public Movie(String title, List<String> genres, int year) {
        this.title = title;
        this.genres = genres;
        this.year = year;
    }

    /**
     * Returns the title of the movie.
     * 
     * @return the movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     * 
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the list of genres associated with the movie.
     * 
     * @return the genres list
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets the list of genres for the movie.
     * 
     * @param genres the new list of genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Returns the year the movie was released.
     * 
     * @return the release year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the release year of the movie.
     * 
     * @param year the new release year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string representation of the {@code Movie} object.
     * 
     * @return a string describing the movie
     */
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", year=" + year +
                '}';
    }
}

