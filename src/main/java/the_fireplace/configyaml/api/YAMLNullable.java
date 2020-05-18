package the_fireplace.configyaml.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Use this for config options you want to make nullable. Most config options should not be null, so a null value will be replaced with a default unless you use this annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLNullable {

}
