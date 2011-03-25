package impl;

import java.util.ArrayList;
import java.util.List;

import api.MovieFinder;
import entity.Movie;

/**
 * The default implementation of movie finder.
 * Simulates a request on a two entries default data support.
 * @author Matthieu Clochard
 */
public class MovieFinderDefault implements MovieFinder {

	/* (non-Javadoc)
	 * @see api.MovieFinder#findAll()
	 */
	public List<Movie> findAll() {
		
		List<Movie> result = new ArrayList<Movie>();

		System.out.println("MovieFinderDefault.findAll()");
		
		result.add(new Movie("John Doe's movie by default", "John Doe"));
		result.add(new Movie("Jane Doe's movie by default", "Jane Doe"));
		
		return result;
		
		
	}
	
	

}
