package org.ai.sentimentAnalysis.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommentsLoader {
    private static final CommentsLoader INSTANCE = new CommentsLoader();
    private CommentsLoader() {

    }
    public static CommentsLoader getInstance() {
        return INSTANCE;
    }
    public List<String> getComments () {
        return getComments(getFileFromResourceAsStream("ttreviews.txt"));
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
    private List<String> getComments(InputStream is) {
        List<String> comments = new ArrayList<>();
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                comments.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
