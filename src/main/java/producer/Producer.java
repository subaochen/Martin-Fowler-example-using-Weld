package producer;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;

import api.MovieFinder;

/**
 * The producers methods of the example
 * @author Matthieu Clochard
 */
public class Producer {
	
	/**
	 * Producers method that returns all the finders
	 * @param finders injected instances of all the finders
	 * @return all the finders on every data support as a List
	 */
	@Produces
	public List<MovieFinder> getAllFinders(@Any Instance<MovieFinder> finders) {		
		List<MovieFinder> result = new ArrayList<MovieFinder>();
		
		for(MovieFinder finder : finders) result.add(finder);
		
		return result;
	}

}
