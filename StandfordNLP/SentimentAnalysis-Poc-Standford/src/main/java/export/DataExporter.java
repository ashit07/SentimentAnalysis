package export;

import config.ConfigProvider;
import config.DefaultConfigProvider;
import config.PropertyLoader;
import org.json.JSONArray;
import parser.OutputParser;
import parser.ParserProviderFactory;

public class DataExporter {
    private ConfigProvider provider = new DefaultConfigProvider(PropertyLoader.propertyLoader);

    public boolean exportData(JSONArray jsonArray) {
        String outputFileType = this.provider.getOutputFileType();
        System.out.println("Writing output type as: "+outputFileType);
        ParserProviderFactory parserProviderFactory = ParserProviderFactory.valueOf(outputFileType);
        if(parserProviderFactory == null) {
            parserProviderFactory = ParserProviderFactory.other;
        }
        OutputParser outputParser = parserProviderFactory.getOutputParser();
        return outputParser.exportData(jsonArray);
    }
}
