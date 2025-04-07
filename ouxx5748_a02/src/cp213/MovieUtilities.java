package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author Zi Feng (Alex) Ou, 169025748, ouxx5748@mylaurier.ca
 * @version 2025-02-10
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {

	// your code here

	int[] count = new int[Movie.GENRES.length];

	for (Movie movie : movies) {
	    if (movie != null) {
		int genre = movie.getGenre();
		if (genre >= 0 && genre < Movie.GENRES.length) {
		    count[genre]++;
		}
	    }
	}

	return count;

    }

    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery
    
    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {

	// your code here

	System.out.print("Title: ");
	String title = keyboard.nextLine();

	System.out.print("Year: ");
	int year = keyboard.nextInt();
	keyboard.nextLine();

	System.out.print("Director: ");
	String director = keyboard.nextLine();

	System.out.print("Rating: ");
	double rating = keyboard.nextDouble();
	keyboard.nextLine();

	System.out.println("Genres:");
	System.out.println(Movie.genresMenu());

	System.out.print("Enter a genre number: ");
	int genre = keyboard.nextInt();

	return new Movie(title, year, director, rating, genre);

    }

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

	// your code here

	ArrayList<Movie> gMovies = new ArrayList<>();

	for (Movie mov : movies) {
	    if (mov.getGenre() == genre) {
		gMovies.add(mov);
	    }
	}

	return gMovies;

    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

	ArrayList<Movie> rMovies = new ArrayList<>();

	for (Movie mov : movies) {
	    if (mov.getRating() >= rating) {
		rMovies.add(mov);
	    }
	}

	return rMovies;

    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

	// your code here

	ArrayList<Movie> yMovies = new ArrayList<>();

	for (Movie mov : movies) {
	    if (mov.getYear() == year) {
		yMovies.add(mov);
	    }
	}

	return yMovies;

    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {

	// your code here

	boolean genreCheck = false;
	int genre = -1;

	System.out.println(Movie.genresMenu());

	while (!genreCheck) {
	    System.out.print("Enter a genre number: ");
	    if (keyboard.hasNextInt()) {
		genre = keyboard.nextInt();
		keyboard.nextLine();
		genreCheck = (genre >= 0 && genre < Movie.GENRES.length);
	    } else {
		keyboard.next();
	    }
	    if (!genreCheck) {
		System.out.println("Invalid Genre");
	    }
	}

	return genre;

    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {

	// your code here

	String[] movieFormat = line.split("\\|");

	String title = movieFormat[0];
	int year = Integer.parseInt(movieFormat[1]);
	String director = movieFormat[2];
	double rating = Double.parseDouble(movieFormat[3]);
	int genre = Integer.parseInt(movieFormat[4]);

	return new Movie(title, year, director, rating, genre);

    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {

	// your code here

	ArrayList<Movie> movies = new ArrayList<>();

	while (fileIn.hasNextLine()) {
	    String line = fileIn.nextLine().trim();
	    if (!line.isEmpty()) {
		Movie movie = readMovie(line);
		movies.add(movie);
	    }
	}

	return movies;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	// your code here

	for (Movie movie : movies) {
	    movie.write(ps);
	}

	return;
    }

}
