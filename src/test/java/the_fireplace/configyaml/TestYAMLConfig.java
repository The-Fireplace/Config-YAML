package the_fireplace.configyaml;

import the_fireplace.configyaml.api.YAMLComment;
import the_fireplace.configyaml.api.YAMLConfig;
import the_fireplace.configyaml.api.YAMLNullable;
import the_fireplace.configyaml.api.constraints.YAMLLengthString;
import the_fireplace.configyaml.api.constraints.YAMLRangeDecimal;
import the_fireplace.configyaml.api.constraints.YAMLRangeNumber;

import java.io.File;

public final class TestYAMLConfig extends YAMLConfig {
    @YAMLNullable
    @YAMLLengthString(min=3, max=20)
    public String testString = "TestValue";
    @YAMLComment("This is a test int")
    @YAMLRangeNumber(min=0, max=500)
    public int testInt = 30;
    @YAMLComment("This is a test double")
    @YAMLRangeDecimal(min=0, max=500)
    public double testDouble = 23.4;

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
