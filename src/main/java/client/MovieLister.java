package client;

import interceptor.ResearchLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import api.MovieFinder;
import api.qualifiers.DataSource;
import api.qualifiers.MovieDataSource;
import entity.Movie;

@ResearchLog
public class MovieLister {

	private MovieFinder finder;
	
	private MovieFinder finderFileSystem;

//	private MovieFinder finderDatabase;
	@Inject @MovieDataSource(DataSource.DATABASE)
	private MovieFinder finderDatabase;
	
	@Inject
	private List<MovieFinder> allFinders;
	
	@Inject @Any
	private Instance<MovieFinder> dynamicFinder;
	
	@Inject @Any
	private Event<Movie> researchEvent;
	
	@Inject
	public MovieLister(MovieFinder finder) {
		this.finder = finder;
	}
//	@Inject
//	public MovieLister(MovieFinder finder, @FileSystem MovieFinder finderFileSystem, @Database MovieFinder finderDatabase) {
//		this.finder = finder;
//		this.finderFileSystem = finderFileSystem;
//		this.finderDatabase = finderDatabase;
//	}
	
	@Inject
	public void setFinderFileSystem(@MovieDataSource(DataSource.FILESYSTEM) MovieFinder finderFileSystem) {
		this.finderFileSystem = finderFileSystem;
	}
	
	public List<Movie> movieDirectedBy(String director) {
		
		List<Movie> allMovies = this.finder.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
				Movie movie = it.next();
	            if (!movie.getDirector().equals(director)) it.remove();
		}
		
		for(Movie movie : allMovies) researchEvent.fire(movie);
        
        return allMovies;
	}
	
	public List<Movie> movieDirectedByInFileSystem(String director) {
		
		List<Movie> allMovies = finderFileSystem.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
			Movie movie = it.next();
            if (!movie.getDirector().equals(director)) it.remove();
		}
        
        return allMovies;
	}
	
	public List<Movie> movieDirectedByInDatabase(String director) {
		
		List<Movie> allMovies = finderDatabase.findAll();
		
		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
			Movie movie = it.next();
            if (!movie.getDirector().equals(director)) it.remove();
		}
        
        return allMovies;
	}
	
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
	
//	public List<Movie> movieDirectedByDynamic(String director) {
//		
//		int test = (new Random()).nextInt(2);
//		
//		@SuppressWarnings("serial")
//		MovieFinder currentFinder = test ==1 ? dynamicFinder.select(new AnnotationLiteral<Database>() {}).get() : dynamicFinder.select(new AnnotationLiteral<FileSystem>() {}).get();
//		List<Movie> allMovies = currentFinder.findAll();
//		
//		for (Iterator<Movie> it = allMovies.iterator(); it.hasNext();) {
//			Movie movie = it.next();
//            if (!movie.getDirector().equals(director)) it.remove();
//		}
//        
//        return allMovies;
//		
////	}
	
}
