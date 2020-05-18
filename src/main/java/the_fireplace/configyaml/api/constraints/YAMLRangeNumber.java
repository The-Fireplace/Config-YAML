package the_fireplace.configyaml.api.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Constrain config value to a range of numbers. When the config is loaded or saved, the game will check this, and if invalid, can error or handle it by setting the value to the closest allowed value depending on what autocorrect is set to.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLRangeNumber {
    long min() default Long.MIN_VALUE;
    long max() default Long.MAX_VALUE;
    boolean print() default true;
    boolean autocorrect() default false;
}
