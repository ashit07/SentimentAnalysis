package config;

public class DefaultConfigProvider implements ConfigProvider{

    private PropertyLoader propertyLoader;
    private static final String INPUT_DIR = "input.dir";
    private static final String OUTPUT_DIR = "output.dir";
    private static final String OUTPUT_FILE_TYPE = "output.type";

    public DefaultConfigProvider(PropertyLoader propertyLoader) {
    this.propertyLoader = propertyLoader;
    }
    @Override
    public String getInputDirectory() {
        return propertyLoader.getProperty(INPUT_DIR);
    }

    @Override
    public String getOutputDirectory() {
        return propertyLoader.getProperty(OUTPUT_DIR);
    }

    @Override
    public String getOutputFileType() {
        return propertyLoader.getProperty(OUTPUT_FILE_TYPE);
    }
}
