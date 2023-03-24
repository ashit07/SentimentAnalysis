package executor;

import export.DataExporter;
import ingress.FilesLoader;
import merge.RecordMerger;
import parser.ParserProviderFactory;
import org.json.JSONArray;
import utils.FileUtility;

import java.io.IOException;
import java.util.List;

public class RecordsMergeExecutor {
    private DataExporter exporter = new DataExporter();
    private RecordMerger merger = new RecordMerger();

    public void mergeRecords() throws IOException {
        List<String> fileNames = FilesLoader.INSTANCE.loadFilesFromInputDir();
        JSONArray records = new JSONArray();
        fileNames.stream().forEach(file -> {
            try {
                records.putAll(convertFile(file));
            } catch (IOException e) {
                System.err.println("Exception while converting file:"+file+" - "+e.getMessage());
            }
        });
        JSONArray mergedRecord = mergeRecords(records);
        exportData(mergedRecord);
    }
    public JSONArray convertFile(String fileName) throws IOException {
        ParserProviderFactory parserProviderFactory = FileUtility.INSTANCE.extractFileType(fileName);
        return parserProviderFactory.getInputParser().parseInput(fileName);
    }
    public JSONArray mergeRecords(JSONArray records) {
        System.out.println(records);
        return merger.mergeRecords(records);
     //   return records;
    }
    public void exportData(JSONArray data) {
        exporter.exportData(data);
    }

    public static void main(String[] args) throws IOException {
        RecordsMergeExecutor executor = new RecordsMergeExecutor();
        executor.mergeRecords();
    }
}
