package the_fireplace.configyaml;

import the_fireplace.configyaml.api.YAMLComment;
import the_fireplace.configyaml.api.YAMLConfig;
import the_fireplace.configyaml.api.YAMLNullable;

import java.io.File;

public final class TestYAMLConfig extends YAMLConfig {
    @YAMLNullable
    public String testString = "TestValue";
    @YAMLComment("This is a test int")
    public int testInt = 30;

    public TestYAMLConfig(String configName) {
        super(configName);
    }

    public TestYAMLConfig(File file) {
        super(file);
    }

    public TestYAMLConfig() {
        this((File)null);
    }

    @Override
    public YAMLConfig getDefaultConfigInstance() {
        return new TestYAMLConfig();
    }
}
