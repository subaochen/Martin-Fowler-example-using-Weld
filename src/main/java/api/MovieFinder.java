package api;

import java.util.List;

import entity.Movie;

/**
 * Service interface for simulation of a movie finder through various supports.
 * @author Matthieu Clochard
 */
public interface MovieFinder {
	/**
	 * Simulates a request on the data support that return all the movies.
	 * @return all the movies as a List.
	 */
	List<Movie> findAll();
}
