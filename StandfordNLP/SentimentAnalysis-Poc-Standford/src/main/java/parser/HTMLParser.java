package parser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.FileUtility;

import java.io.IOException;

public class HTMLParser implements InputParser, OutputParser{

    public void parseHTML() throws IOException {
        String HTML = FileUtility.INSTANCE.loadFile("files//first.html");
//        System.out.println(HTML);
        Document document = Jsoup.parse(HTML);
        Elements tables = document.select("table");
        JSONArray tablesArr = new JSONArray();

        tables.forEach( table -> {
            Elements tableRows = document.select("tr");
            Elements headers = tableRows.select("th");
            Elements values = tableRows.select("td");
            JSONArray jsonArray = new JSONArray();
            int totalHeaders = headers.size();
            int totalValues = values.size();
            int numberOfRecords = totalValues/totalHeaders;
            int headerCounter = 0;
            int valCounter = 0;
            while(headerCounter< numberOfRecords) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < totalHeaders; i++) {
                    jsonObject.put(headers.get(i).text(), values.get(valCounter).text());
                    valCounter++;
                }
                headerCounter++;
                jsonArray.put(jsonObject);
            }
            tablesArr.put(jsonArray);
        });
        System.out.println(tablesArr.toString());
    }

    @Override
    public JSONArray parseInput(String fileName) throws IOException {
        String HTML = FileUtility.INSTANCE.loadFile(fileName);
//        System.out.println(HTML);
        Document document = Jsoup.parse(HTML);
        Elements tables = document.select("table");
        JSONArray tablesArr = new JSONArray();

        tables.forEach( table -> {
            Elements tableRows = document.select("tr");
            Elements headers = tableRows.select("th");
            Elements values = tableRows.select("td");
 //           JSONArray jsonArray = new JSONArray();
            int totalHeaders = headers.size();
            int totalValues = values.size();
            int numberOfRecords = totalValues/totalHeaders;
            int headerCounter = 0;
            int valCounter = 0;
            while(headerCounter< numberOfRecords) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < totalHeaders; i++) {
                    jsonObject.put(headers.get(i).text(), values.get(valCounter).text());
                    valCounter++;
                }
                headerCounter++;
                tablesArr.put(jsonObject);
            }
   //         tablesArr.put(jsonArray);
        });

        System.out.println(tablesArr.toString());
        return tablesArr;
    }
    public static void main(String[] args) throws IOException {
        HTMLParser parser = new HTMLParser();
        parser.parseHTML();
    }


    @Override
    public boolean exportData(JSONArray jsonArray) {
        throw new UnsupportedOperationException("Export not allowed for this type");
    }
}
