package the_fireplace.configyaml.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Use this for variables you do NOT want showing up in the config file. This is only needed for instance variables; static variables will not show up in the config file.
 * DO NOT use this on generic objects, only types with a tag according to {@link the_fireplace.configyaml.TagUtils#hasTag(Class)}. E.G. ints, strings, lists, and maps are all ok, but your custom Car object that holds three variables isn't.
 * I plan to make this work on generic objects at some point in the future, but they are not currently supported and doing it right now will cause a broken output and/or crashing.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface YAMLExclude {

}
