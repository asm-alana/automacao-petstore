package api;

import java.util.HashMap;
import java.util.Map;

public class ApiHeaders {

    Map<String, String> headers = new HashMap<>();

    public Map<String, String> petstoreHeaders(){
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
