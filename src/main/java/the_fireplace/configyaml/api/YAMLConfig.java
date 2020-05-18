package the_fireplace.configyaml.api;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import the_fireplace.configyaml.ConfigYaml;
import the_fireplace.configyaml.TagUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract config file with the ability to load, fill in updated defaults, and save. Classes inheriting this MUST have a constructor which takes no parameters. They may have other constructors as well, but one which takes no parameters must remain.
 */
public abstract class YAMLConfig {
    protected File configFile;

    /**
     * Constructor that takes a file name to look for in the config directory, useful constructor for basic Minecraft mod configs. Don't forget to call {@link YAMLConfig#load()} on your newly constructed object to load from the file.
     * @param configName
     */
    public YAMLConfig(String configName) {
        this(new File("config", configName+".yml"));
    }

    /**
     * Pass in null to skip loading from a file, useful for creating a default config object. Don't forget to then call {@link YAMLConfig#load()} to load from the file.
     * @param file
     */
    public YAMLConfig(File file) {
        this.configFile = file;
    }

    public void save() {
        if(configFile != null) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new ConfigYaml(options);

            configFile.getParentFile().mkdirs();
            try {
                FileWriter writer = new FileWriter(configFile);
                yaml.dump(this, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        if(configFile != null) {
            if (configFile.exists()) {
                Yaml yaml = new ConfigYaml(new Constructor(this.getClass()));
                try {
                    FileReader reader = new FileReader(configFile);
                    merge(this, yaml.load(reader), true);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                merge(this, getDefaultConfigInstance(), false);
            }
            //Write config because it may have been updated since last time
            save();
        }
    }

    public abstract YAMLConfig getDefaultConfigInstance();

    /**
     * Fills in the values in priority with values from alternative.
     * @param priority
     * The main config to merge. This should typically be your actual config instance.
     * @param alternative
     * The default config to fill in the blanks from.
     * @param overwrite
     * If true, this will overwrite values from priority with ones from alternative.
     * If false, this will overwrite null values that aren't marked with {@link YAMLNullable}.
     * @return priority for convenience. This does not operate on a copy, so using this return value isn't absolutely necessary.
     */
    public static <T> T merge(T priority, T alternative, boolean overwrite) {
        return merge(priority, alternative, overwrite, new ArrayList<>());
    }

    private static <T> T merge(T priority, T alternative, boolean overwrite, List<Object> visited) {
        if(!priority.getClass().isAssignableFrom(alternative.getClass()))
            throw new IllegalArgumentException("The priority object must be assignable from the alternative's class.");
        //Skip if we've already visited this object, we don't want to get stuck in a loop
        if(!visited.contains(priority)) {
            visited.add(priority);
            for (Field f : alternative.getClass().getFields()) {
                if (Modifier.isStatic(f.getModifiers()))
                    continue;
                Field pf;
                try {
                    pf = priority.getClass().getField(f.getName());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    continue;
                }
                pf.setAccessible(true);
                boolean hasNullAnnotation = pf.getAnnotation(YAMLNullable.class) != null;
                if (TagUtils.hasTag(pf.getType())) {
                    try {
                        Object priorityValue = pf.get(priority);
                        Object alternativeValue = pf.get(alternative);
                        if ((priorityValue == null && !hasNullAnnotation) || overwrite && (alternativeValue != null || hasNullAnnotation))
                            pf.set(priority, alternativeValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Object o = pf.get(priority);
                        if (o == null && !hasNullAnnotation)
                            pf.set(priority, pf.get(alternative));
                        else if (!hasNullAnnotation)
                            merge(o, pf.get(alternative), overwrite, visited);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                pf.setAccessible(false);
            }
        }
        return priority;
    }
}
