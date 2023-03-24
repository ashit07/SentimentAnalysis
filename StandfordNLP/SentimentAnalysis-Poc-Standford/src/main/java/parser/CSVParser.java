package parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import config.ConfigProvider;
import config.DefaultConfigProvider;
import config.PropertyLoader;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.FileUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class CSVParser implements InputParser, OutputParser{
    private ConfigProvider configProvider = new DefaultConfigProvider(PropertyLoader.propertyLoader);
    @Override
    public JSONArray parseInput(String fileName) throws IOException {
            byte[] input = FileUtility.INSTANCE.loadFileBytes(fileName);
            CsvSchema csv = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<?, ?>> list = mappingIterator.readAll();
            JSONArray jsonArray = new JSONArray();
            list.forEach(val -> {
                JSONObject jsonObject = new JSONObject(val);
                jsonArray.put(jsonObject);
            });
//            jsonArray.putAll(list);
            System.out.println(jsonArray);
            return jsonArray;
    }

    @Override
    public boolean exportData(JSONArray jsonArray) {
        try {
            String outputFile = FileUtility.INSTANCE.getOutputFileName(configProvider.getOutputFileType(), configProvider.getOutputDirectory());
            if(Files.notExists(Path.of(outputFile))) {
                Files.createFile(Path.of(outputFile));
            }
            String csv = CDL.toString(jsonArray);
            System.out.println("CSV Data: "+csv);
            Files.writeString(Path.of(outputFile), csv);
            System.out.println("Data has been Sucessfully Written to "+ outputFile);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        CSVParser csvParser = new CSVParser();
        csvParser.parseInput("C:\\Users\\ajuneja\\Documents\\Learnings\\AI\\SentimentAnalysis\\StandfordNLP\\SentimentAnalysis-Poc-Standford\\src\\main\\resources\\files\\second.csv");
    }

}
