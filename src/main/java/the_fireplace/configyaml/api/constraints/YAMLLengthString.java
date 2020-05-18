package the_fireplace.configyaml.api.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Constrain config value to a range of string length. When the config is loaded or saved, the game will check this, and will error if invalid.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLLengthString {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    boolean print() default true;
}
