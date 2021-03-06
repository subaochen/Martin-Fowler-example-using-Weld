package api.qualifiers;

/**
 * The enumeration of all the simulated data supports used by the annotation {@link MovieDataSource}.
 * @author Matthieu Clochard
 */
public enum DataSource {
	/**
	 * Dynamically found data support.
	 */
	DYNAMIC,
	/**
	 * Database data support.
	 */
	DATABASE,
	/**
	 * File system data support.
	 */
	FILESYSTEM
}
