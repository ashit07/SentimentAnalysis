package merge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class RecordMerger {
    private static final String KEY_NAME = "ID";
    public JSONArray mergeRecords(JSONArray records) {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for(int i=0; i<records.length(); i++) {
            jsonObjectList.add(records.getJSONObject(i));
        }

        // Collect JSONObjects with same ID
        Map<String, List<JSONObject>> categorizeRecordsWithId = new HashMap<>();
        jsonObjectList.forEach(jsonObject -> {
            String key = jsonObject.getString(KEY_NAME);
            if(categorizeRecordsWithId.containsKey(key)) {
                categorizeRecordsWithId.get(key).add(jsonObject);
            } else {
                List<JSONObject> jsonObjects = new ArrayList<>();
                jsonObjects.add(jsonObject);
                categorizeRecordsWithId.put(key, jsonObjects);
            }
        });
        System.out.println(categorizeRecordsWithId);

        List<JSONObject> mergedList = mergeRecordsWithSameId(categorizeRecordsWithId);

        // Sort based on id
        Collections.sort(mergedList, getComparator());

        JSONArray array = new JSONArray(mergedList);
        return array;
    }

    private List<JSONObject> mergeRecordsWithSameId( Map<String, List<JSONObject>> categorizedRecordsWithId){
        List<JSONObject> mergedList = new ArrayList<>();
        Set<String> headers = new HashSet<>();
        categorizedRecordsWithId.entrySet().forEach( entry -> {
                    List<JSONObject> vals = entry.getValue();
                    vals.forEach(val -> {
                        headers.addAll(Arrays.asList(JSONObject.getNames(val)));
                    });
        });

        System.out.println("---------------------");
        System.out.println("Headers are: "+headers);
        System.out.println("---------------------");

        categorizedRecordsWithId.entrySet().forEach( entry -> {
            List<JSONObject> vals = entry.getValue();
            JSONObject merged = new JSONObject();
            vals.forEach( val -> {
                for(String key : headers) {
                    Object value = null;
                    if(merged.has(key) && merged.get(key) != "") {
                        continue;
                    }
                    if(val.has(key)) {
                        value = val.get(key);
                    } else {
                        value = "";
                    }
                    merged.put(key, value);
                }
            });
            mergedList.add(merged);
        });
        System.out.println("---------------------");
        System.out.println(mergedList);
        System.out.println("---------------------");
        return mergedList;
    }

    public Comparator<JSONObject> getComparator() {
       return new Comparator<JSONObject>() {
            //You can change "Name" with "ID" if you want to sort by ID
            private static final String KEY_NAME1 = "ID";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME1);
                    valB = (String) b.get(KEY_NAME1);
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
            }
        };
    }
}
