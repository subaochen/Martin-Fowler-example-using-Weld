package client;

import interceptor.ResearchLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import api.MovieFinder;
import api.qualifiers.DataSource;
import api.qualifiers.MovieDataSource;
import api.qualifiers.MovieDataSourceAnnotationLiteral;
import entity.Movie;

/**
 * The client of the example. It defines methods that search movies on various
 * data supports.
 * This class is annotated {@link ResearchLog} so every call of its methods is
 * logged.
 * @author Matthieu Clochard
 */
@ResearchLog
public class MovieLister {

	/**
	 * The default finder. Injected using constructor injection.
	 */
	private MovieFinder finder;
	
	/**
	 * The file system finder. Injected using mutator injection.
	 */
	private MovieFinder finderFileSystem;
	
	/**
	 * The database finder. Injected using field injection.
	 */
	@Inject @MovieDataSource(DataSource.DATABASE)
	private MovieFinder finderDatabase;
	
	/**
	 * The list of all finders. Injected using producers method.
	 */
	@Inject
	private List<MovieFinder> allFinders;
	
	/**
	 * The dynamically injected finder using programmatic lookup.
	 */
	@Inject @Any
	private Instance<MovieFinder> dynamicFinder;
	
	/**
	 * The event corresponding to a done research.
	 */
	@Inject @Any
	private Event<Movie> researchEvent;
	
	/**
	 * The injection constructor. Injects the default finder.
	 * @param finder the default injected finder.
	 */
	@Inject
	public MovieLister(MovieFinder finder) {
		this.finder = finder;
	}
	
	/*
	 * The all fields injection constructor example
	 */
//	@Inject
//	public MovieLister(MovieFinder finder, @FileSystem MovieFinder finderFileSystem, @Database MovieFinder finderDatabase) {
//		this.finder = finder;
//		this.finderFileSystem = finderFileSystem;
//		this.finderDatabase = finderDatabase;
//	}
	
	/**
	 * The injection mutator for file system finder.
	 * Inject it using the correct qualifier.
	 * @param finderFileSystem
	 */
	@Inject
	public void setFinderFileSystem(@MovieDataSource(DataSource.FILESYSTEM) MovieFinder finderFileSystem) {
		this.finderFileSystem = finderFileSystem;
	}
	
	/**
	 * The default research by director method.
	 * Uses the default injected finder.
	 * Fires an event for each result of the request.
	 * @param director the director name whose movies are researched.
	 * @return all the movies of this director on the default data support as a
	 * List.
	 */
	public List<Movie> movieDirectedBy(String director) {
		
		List<Movie> allMovies = this.finder.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
				Movie movie = it.next();
	            if (!movie.getDirector().equals(director)) it.remove();
		}
		
		for(Movie movie : allMovies) researchEvent.fire(movie);
        
        return allMovies;
	}
	
	/**
	 * The file system research by director method.
	 * Uses the file system injected finder.
	 * @param director the director name whose movies are researched.
	 * @return all the movies of this director on the file system data support 
	 * as a List.
	 */
	public List<Movie> movieDirectedByInFileSystem(String director) {
		
		List<Movie> allMovies = finderFileSystem.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
			Movie movie = it.next();
            if (!movie.getDirector().equals(director)) it.remove();
		}
        
        return allMovies;
	}
	
	/**
	 * The database research by director method.
	 * Uses the database injected finder.
	 * @param director the director name whose movies are researched.
	 * @return all the movies of this director on the database data support as a
	 *  List.
	 */
	public List<Movie> movieDirectedByInDatabase(String director) {
		
		List<Movie> allMovies = finderDatabase.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
			Movie movie = it.next();
            if (!movie.getDirector().equals(director)) it.remove();
		}
        
        return allMovies;
	}
	
	/**
	 * The global research by director method.
	 * Uses all injected finder.
	 * @param director the director name whose movies are researched.
	 * @return all the movies of this director on any data support as a List.
	 */
	public List<Movie> movieDirectedByEverywhere(String director) {
		
		List<Movie> result = new ArrayList<Movie>();
		
		for(MovieFinder finder : allFinders) {
			List<Movie> allMovies = finder.findAll();
			
			for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
				Movie movie = it.next();
	            if (!movie.getDirector().equals(director)) it.remove();
			}
			
			result.addAll(allMovies);
		}
        
        return result;
	}
	
	/**
	 * The dynamic research by director method.
	 * Uses the dynamically injected finder.
	 * This finder is selected randomly between database and file system.
	 * @param director the director name whose movies are researched.
	 * @return all the movies of this director on any data support as a List.
	 */
	public List<Movie> movieDirectedByDynamic(String director) {
		
		int test = (new Random()).nextInt(2);
		
		MovieDataSourceAnnotationLiteral database = new MovieDataSourceAnnotationLiteral() {
			
			public DataSource value() {
				return DataSource.DATABASE;
			}
		};
		MovieDataSourceAnnotationLiteral fileSystem = new MovieDataSourceAnnotationLiteral() {
			
			public DataSource value() {
				return DataSource.FILESYSTEM;
			}
		};
		MovieFinder currentFinder = test ==1 ? dynamicFinder.select(database).get() : dynamicFinder.select(fileSystem).get();
		List<Movie> allMovies = currentFinder.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
			Movie movie = it.next();
            if (!movie.getDirector().equals(director)) it.remove();
		}
        
        return allMovies;
		
	}
	
}
