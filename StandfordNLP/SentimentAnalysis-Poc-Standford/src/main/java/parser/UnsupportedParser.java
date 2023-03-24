package parser;

import org.json.JSONArray;

import java.io.IOException;

public class UnsupportedParser implements InputParser, OutputParser{

    @Override
    public JSONArray parseInput(String fileName) throws IOException {
        throw new UnsupportedOperationException("The file for this type is not supported yet: "+fileName);
    }

    @Override
    public boolean exportData(JSONArray jsonArray) {
        throw new UnsupportedOperationException("The export for this type is not supported yet.");
    }
}
