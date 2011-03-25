package impl;

import java.util.ArrayList;
import java.util.List;

import api.MovieFinder;
import api.qualifiers.DataSource;
import api.qualifiers.MovieDataSource;
import entity.Movie;

/**
 * The file system data support implementation of movie finder.
 * Simulates a request on a two entries file system  data support.
 * @author Matthieu Clochard
 */
@MovieDataSource(DataSource.FILESYSTEM)
public class MovieFinderInFileSystem implements MovieFinder {
	
	/* (non-Javadoc)
	 * @see api.MovieFinder#findAll()
	 */
	public List<Movie> findAll() {
		
		List<Movie> result = new ArrayList<Movie>();

		System.out.println("MovieFinderInFileSystem.findAll()");
		
		result.add(new Movie("John Doe's movie in file system", "John Doe"));
		result.add(new Movie("Jane Doe's movie in file system", "Jane Doe"));
		
		return result;
		
		
	}

}
