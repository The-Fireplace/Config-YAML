package the_fireplace.configyaml;

public class ConfigIOTests {

    public static void main(String[] args) {
        createConfig();
        loadConfig();
        saveAndLoadConfig();
    }

    public static void createConfig() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        assert config1.testInt == 30;
        assert "TestValue".equals(config1.testString);
        config1.save();
    }

    public static void loadConfig() {
        new TestYAMLConfig("test1");
        //load from test1 and see if the values loaded properly
        TestYAMLConfig config2 = new TestYAMLConfig("test1");
        assert config2.testInt == 30;
        assert "TestValue".equals(config2.testString);
        config2.save();
    }

    public static void saveAndLoadConfig() {
        TestYAMLConfig config1 = new TestYAMLConfig("test1");
        config1.testInt = 40;
        config1.testString = "ValueTest";
        config1.save();
        //load from test1 and see if the values loaded properly
        TestYAMLConfig config2 = new TestYAMLConfig("test1");
        config2.load();
        assert config2.testInt == 40;
        assert "ValueTest".equals(config2.testString);
        config2.save();
    }
}
