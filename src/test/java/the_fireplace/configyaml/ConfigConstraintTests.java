package the_fireplace.configyaml;

public class ConfigConstraintTests {

    public static void main(String[] args) {
        saveConstrainedConfig_ValidValues();
        saveConstrainedConfig_InvalidDecimal();
        saveConstrainedConfig_InvalidNumber();
        saveConstrainedConfig_InvalidString();
        saveConstrainedConfig_InvalidAll();
    }

    public static void saveConstrainedConfig_ValidValues() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.hardFail = false;
        config1.save();
    }

    public static void saveConstrainedConfig_InvalidString() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.hardFail = false;
        config1.testString = "123456789012345678901234567890";
        config1.save();
    }

    public static void saveConstrainedConfig_InvalidNumber() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.hardFail = false;
        config1.testInt = 999999999;
        config1.save();
    }

    public static void saveConstrainedConfig_InvalidDecimal() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.hardFail = false;
        config1.testDouble = 999999999.99;
        config1.save();
    }

    public static void saveConstrainedConfig_InvalidAll() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.hardFail = false;
        config1.testInt = 999999999;
        config1.testDouble = 999999999.99;
        config1.testString = "123456789012345678901234567890";
        config1.save();
    }
}
