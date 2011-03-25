package api.qualifiers;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Helper class using for dynamic selection of bean instance with programmatic 
 * lookup.
 * @author Matthieu Clochard
 */
public abstract class MovieDataSourceAnnotationLiteral extends AnnotationLiteral<MovieDataSource> implements MovieDataSource {
}
