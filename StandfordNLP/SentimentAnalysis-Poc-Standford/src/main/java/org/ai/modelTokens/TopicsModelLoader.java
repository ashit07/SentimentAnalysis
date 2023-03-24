package org.ai.modelTokens;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TopicsModelLoader {
    private static final TopicsModelLoader INSTANCE = new TopicsModelLoader();
    private static final ObjectMapper mapper = new ObjectMapper();
    private TopicsModelLoader() {

    }
    public static TopicsModelLoader getInstance() {
        return INSTANCE;
    }
    public JsonNode getModel (List<String> data) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return mapper.readTree(classLoader.getResourceAsStream("features.json").readAllBytes());
    }

//
//    private String getFileFromResourceAsStream(String fileName) throws IOException {
//
//        // The class loader that loaded the class
//        ClassLoader classLoader = getClass().getClassLoader();
//
//        return new String(Files.readAllBytes(Path.of("features.json")));
//
//    }
}
