package ingress;

import config.ConfigProvider;
import config.DefaultConfigProvider;
import config.PropertyLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilesLoader {
    public static final FilesLoader INSTANCE = new FilesLoader();

    private ConfigProvider configProvider;
    private FilesLoader() {
        configProvider = new DefaultConfigProvider(PropertyLoader.propertyLoader);
    }

    public List<String> loadFilesFromInputDir() throws IOException {
        String inputLocation = configProvider.getInputDirectory();
        List<String> inputFiles = new ArrayList<>();
        System.out.println("Input location: "+inputLocation);
        try (Stream<Path> paths = Files.walk(Paths.get(inputLocation))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        inputFiles.add(path.toFile().getAbsolutePath());
                    });
        }
        return inputFiles;
    }

    public static void main(String[] args) throws IOException {
        FilesLoader.INSTANCE.loadFilesFromInputDir();
    }
}
