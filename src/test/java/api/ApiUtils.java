package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import utils.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiUtils extends LogUtils {

    protected static Response response;
    protected static JSONObject body;
    protected static String uri;
    protected static Map<String, String> headers = new HashMap<>();

    public void log(String verbo){

        super.logInfo("DADOS ENVIADOS NO REQUEST");
        super.logInfo(verbo + "" + uri);
        super.logInfo("Body: \n" + body);
        super.logInfo("Headers: \n" + headers);

        super.logInfo("DADOS RECEBIDOS");
        super.logInfo("Status code: " + response.statusCode());
        super.logInfo("Payload recebido: \n" + response.asPrettyString());
        super.logInfo("Tempo de resposta" + response.timeIn(TimeUnit.MILLISECONDS));
    }

}
