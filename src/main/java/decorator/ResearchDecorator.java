package decorator;

import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import api.MovieFinder;
import entity.Movie;

/**
 * Decorator example. Decorates the all movies research method by the injection 
 * of a new film for every result.
 * @author Matthieu Clochard
 */
@Decorator
public abstract class ResearchDecorator implements MovieFinder {
	
	/**
	 * The delegate finder.
	 */
	@Inject @Delegate @Any 
	private MovieFinder finder;

	/* (non-Javadoc)
	 * @see api.MovieFinder#findAll()
	 */
	public final List<Movie> findAll() {

		System.out.println("~\t~Décoration : ");
		System.out.println("~\t~\tMéthode : " + finder.getClass() + ".findAll()");
		System.out.println("~\t~\tAjout d'un film de John Doe: \"Decorator's movie\"");
		
		List<Movie> result = finder.findAll();
		result.add(new Movie("Decorator's movie", "John Doe"));
		
		return result;
	}

}
