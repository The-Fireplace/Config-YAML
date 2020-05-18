package the_fireplace.configyaml;

import org.yaml.snakeyaml.nodes.Tag;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagUtils {
    public static boolean hasTag(Class<?> checkClass) {
        //noinspection UseOfObsoleteDateTimeApi
        if(checkClass.isPrimitive()
            || checkClass.isAssignableFrom(String.class)
            || checkClass.isAssignableFrom(List.class)
            || checkClass.isArray()
            || checkClass.isAssignableFrom(Map.class)
            || checkClass.isAssignableFrom(Set.class)
            || checkClass.isAssignableFrom(Date.class)
            || checkClass.isAssignableFrom(java.sql.Date.class)
            || checkClass.isAssignableFrom(Timestamp.class))
            return true;
        boolean hasTag = false;
        for(Map.Entry<Tag, Set<Class<?>>> entry: Tag.COMPATIBILITY_MAP.entrySet()) {
            for (Class<?> c : entry.getValue()) {
                if(checkClass.isAssignableFrom(c)) {
                    hasTag = true;
                    break;
                }
            }
            if(hasTag)
                break;
        }
        return hasTag;
    }
}
