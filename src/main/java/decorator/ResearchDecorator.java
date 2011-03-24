package decorator;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import api.MovieFinder;
import entity.Movie;

@Decorator
public abstract class ResearchDecorator implements MovieFinder {
	
	@Inject @Delegate @Any MovieFinder finder;

	public List<Movie> findAll() {

		System.err.println("Décoration : ");
		System.err.println("\tMéthode : " + finder.getClass() + ".findAll()");
		System.err.println("\tAjout d'un film de John Doe: \"Decorator's movie\"");
		
		List<Movie> result = finder.findAll();
		result.add(new Movie("Decorator's movie", "John Doe"));
		
		return result;
	}

}
