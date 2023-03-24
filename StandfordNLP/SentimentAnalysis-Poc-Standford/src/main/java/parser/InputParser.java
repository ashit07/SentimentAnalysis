package parser;

import org.json.JSONArray;

import java.io.IOException;

public interface InputParser {

    public JSONArray parseInput(String fileName) throws IOException;
}
