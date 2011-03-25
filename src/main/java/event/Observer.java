package event;

import javax.enterprise.event.Observes;

import entity.Movie;

/**
 * Example class for catching event.
 * Catches event fired by the default research method.
 * @author Matthieu Clochard
 */
public class Observer {
	
	/**
	 * Catches all event fired using a movie (like the default research method).
	 * Display a message for each researched movie.
	 * @param movie the movie that has been researched.
	 */
	public void onResearch(@Observes Movie movie) {
		System.out.println("@\t@Un événement est observé, le film : " + movie.getTitle() + " a été recherché !");
	}

}
