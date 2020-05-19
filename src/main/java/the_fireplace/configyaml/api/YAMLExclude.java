package the_fireplace.configyaml.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Use this for variables you do NOT want showing up in the config file. This is only needed for instance variables; static variables will not show up in the config file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLExclude {

}
