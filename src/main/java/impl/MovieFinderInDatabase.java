package impl;

import java.util.ArrayList;
import java.util.List;

import api.MovieFinder;
import api.qualifiers.DataSource;
import api.qualifiers.MovieDataSource;
import entity.Movie;

@MovieDataSource(DataSource.DATABASE)
public class MovieFinderInDatabase implements MovieFinder {
	
	public List<Movie> findAll() {
		
		List<Movie> result = new ArrayList<Movie>();

		System.out.println("MovieFinderInDatabase.findAll()");
		
		result.add(new Movie("John Doe's movie in database", "John Doe"));
		result.add(new Movie("Jane Doe's movie in database", "Jane Doe"));
		result.add(new Movie("Other rare John Doe's movie only in database", "John Doe"));
		
		return result;
		
		
	}

}
