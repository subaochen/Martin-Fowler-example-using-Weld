package main;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import client.MovieLister;
import entity.Movie;

/**
 * The entry point of the example. Show the processing of CDI mechanisms.
 * @author Matthieu Clochard
 */
public class Test {
	
	/**
	 * The injected client movie lister.
	 */
	@Inject
	private MovieLister movieLister;

	/**
	 * The entry point method of the example.
	 * Called right after Weld container initialization.
	 * @param event the event fired by the Weld container after his 
	 * initialization.
	 */
	public void start(@Observes ContainerInitialized event) {
		
		System.out.println("Containe initialized. Test beging ...\n");
		
		List<Movie> result;
		
		System.out.println("================================================================================");
		System.out.println("Default research for John Doe's movies: ");
		result = movieLister.movieDirectedBy("John Doe");
		System.out.println("\nSearch result : ");
		for(Movie movie : result) {
			System.out.println("\t" + movie);
		}
		System.out.println("================================================================================");
		System.out.println("Database research for John Doe's movies: ");
		result = movieLister.movieDirectedByInDatabase("John Doe");
		System.out.println("\nSearch result : ");
		for(Movie movie : result) {
			System.out.println("\t" + movie);
		}
		System.out.println("================================================================================");
		System.out.println("File system research for John Doe's movies: ");
		result = movieLister.movieDirectedByInFileSystem("John Doe");
		System.out.println("\nSearch result : ");
		for(Movie movie : result) {
			System.out.println("\t" + movie);
		}
		System.out.println("================================================================================");
		System.out.println("Dynamic research for John Doe's movies: ");
		result = movieLister.movieDirectedByDynamic("John Doe");
		System.out.println("\nSearch result : ");
		for(Movie movie : result) {
			System.out.println("\t" + movie);
		}
		System.out.println("================================================================================");
		System.out.println("Global research for John Doe's movies: ");
		result = movieLister.movieDirectedByEverywhere("John Doe");
		System.out.println("\nSearch result : ");
		for(Movie movie : result) {
			System.out.println("\t" + movie);
		}
		System.out.println("================================================================================");
		
		System.out.println("\nTest ending ...");
		
	}
	
}
