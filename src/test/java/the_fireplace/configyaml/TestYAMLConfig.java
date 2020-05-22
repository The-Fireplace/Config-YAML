package the_fireplace.configyaml;

import the_fireplace.configyaml.api.YAMLComment;
import the_fireplace.configyaml.api.YAMLConfig;
import the_fireplace.configyaml.api.YAMLExclude;
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

    @YAMLComment("This is a category.\nIt can be used to keep config organized.")
    public Category cat = new Category();

    //@YAMLExclude
    //@YAMLComment("This is an excluded object.\nIt should not show up.")
    //public Category excludedCat = new Category();

    public static class Category {
        @YAMLComment("Here we have a test boolean")
        public boolean testBool1 = false;
        @YAMLExclude
        @YAMLComment("Here we have another test boolean. This should not show up in the file.")
        public boolean testBool2 = true;
        @YAMLComment("Here we have a third boolean")
        public boolean testBool3 = true;
    }

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
