package utils;

import parser.ParserProviderFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

public class FileUtility {
    public static final FileUtility INSTANCE = new FileUtility();

    private FileUtility() {
    }
    public String loadFile(String fileName) throws IOException {
            return new String(loadFileBytes(fileName));
    }
    public byte[] loadFileBytes(String fileName) throws IOException {
        System.out.println("Loading file: "+fileName);
        return Files.readAllBytes(Path.of(fileName));
//        ClassLoader classLoader = getClass().getClassLoader();
//        return classLoader.getResourceAsStream(fileName).readAllBytes();
    }
    public ParserProviderFactory extractFileType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println("Extension: "+extension);
        ParserProviderFactory parserProviderFactory = ParserProviderFactory.valueOf(extension);
        if(parserProviderFactory == null) {
            parserProviderFactory = ParserProviderFactory.other;
        }
        return parserProviderFactory;
    }
    public String getOutputFileName(String outputFileType, String outputFileDir) {
        return getOutputFileName(outputFileType, outputFileDir, 0);
    }
    public String getOutputFileName(String outputFileType, String outputFileDir, int fileNumber) {
        if(fileNumber<0) {
            fileNumber = 0;
        }
        String outputFileName = Constants.OUTPUT_FILE_PREFIX+fileNumber+"."+outputFileType;
        if(Files.exists(Path.of(outputFileDir+"\\"+outputFileName))) {
            fileNumber = fileNumber+ (ThreadLocalRandom.current().nextInt(0, 100));
            return getOutputFileName(outputFileType, outputFileDir, fileNumber);
        }
        return outputFileDir+"\\"+outputFileName;
    }
}
