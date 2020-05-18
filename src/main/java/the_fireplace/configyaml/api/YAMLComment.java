package the_fireplace.configyaml.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * An interface for adding comments to your YAML. Annotate fields you want commented with this. Use \n to separate lines for multiline comments.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLComment {
    String value() default "";
}
