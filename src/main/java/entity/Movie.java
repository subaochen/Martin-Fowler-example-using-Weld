package entity;

/**
 * A movie represented by its title and director.
 * @author Matthieu Clochard
 */
public class Movie {

	/**
	 * The movie title.
	 */
	private String title;
	
	/**
	 * The movie director.
	 */
	private String director;

	/**
	 * Constructor using a title and a director.
	 * @param title the title of the new movie.
	 * @param director the director of the new movie.
	 */
	public Movie(String title, String director) {
		this.title = title;
		this.director = director;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Movie : [title : " + this.title + ", director : " + this.director + "], @ : " + super.toString();
	}
	
	
}
