package org.ai.modelTokens;

import com.fasterxml.jackson.databind.JsonNode;
import edu.stanford.nlp.util.StringUtils;

import javax.json.JsonArray;
import java.io.IOException;
import java.util.*;

public class FeatureParser {

    public Set<String> extractFeatures(List<String> data) throws IOException {
        List<String> parsedFeatures = new ArrayList<>();
        JsonNode featureData = getFeaturesFromServer(data);
        List<String> unparsedFeatures = new ArrayList<>();
        for (Iterator<JsonNode> it = featureData.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();
      //      System.out.println(jsonNode);
            for (Iterator<JsonNode> it1 = jsonNode.elements(); it1.hasNext(); ) {
                JsonNode innerNode = it1.next();
                if(innerNode.isNumber()) {
                    continue;
                }
                unparsedFeatures.add(innerNode.asText());
            }
        }
        System.out.println(unparsedFeatures);
        Set<String> keywords = extractKeywords(unparsedFeatures);
        System.out.println("--------------");
        System.out.println(keywords);
        return keywords;
    }

    private Set<String> extractKeywords(List<String> unparsedFeatures) {
        Set<String> keywords = new HashSet<>();
        for(String unparsedStr : unparsedFeatures) {
            String temp = unparsedStr;
            temp = temp.replaceAll("[^a-z | /+ | /]", "");
            System.out.println("------------------");
            System.out.println(temp);
            String[] key = temp.split("\\+");
            System.out.println(key);
            System.out.println("----------------");
            keywords.addAll(Arrays.asList(key));
        }
        return keywords;
    }



    private JsonNode getFeaturesFromServer(List<String> data) throws IOException {
        return TopicsModelLoader.getInstance().getModel(data);
    }

    public static void main(String[] args) throws IOException {
        FeatureParser obj = new FeatureParser();
        obj.extractFeatures(new ArrayList<>());

//        String abc = "paddle + spin + good + beginner + well + great + work + play + buy + little";
//        List<String> strings = StringUtils.split(abc, "\\+");
//
//        System.out.println(strings);
    }
}
