package api.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Annotation qualifier in order to select the data support in use. 
 * Data supports are enumerated here : {@link DataSource}.
 * @author Matthieu Clochard
 */
@Target( { TYPE, METHOD, PARAMETER, FIELD })
@Retention(RUNTIME)
@Qualifier
public @interface MovieDataSource {	
	/**
	 * The data supports in use.
	 * @return the data support in use as a member of {@link DataSource}.
	 */
	DataSource value() default DataSource.DYNAMIC;
}
