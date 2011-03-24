package main;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import client.MovieLister;
import entity.Movie;

public class Test {
	
	@Inject
	private MovieLister movieLister;

	public void start(@Observes ContainerInitialized event) {
		
		System.out.println("Containe initialized. Test beging ...");
		
		List<Movie> result;
		
		System.out.println("Test ending ...");
		
	}
	
}
