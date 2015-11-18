package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.JsonItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by akipkoech on 3/6/15.
 */
public abstract class AbstractHandler {

    protected final Logger  logger = LoggerFactory.getLogger(this.getClass());

    public static String getJsonSuccessData(List<? extends JsonItem> results){

        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(JsonItem ji: results){
            arrayBuilder.add(ji.toJson());
        }

        builder.add("data", arrayBuilder);
        return toJsonString(builder.build());
    }

    public static String getJsonSuccessData(JsonItem jsonItem){

        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success",true)
                .add("data", jsonItem.toJson());

        return toJsonString(builder.build());
    }

    public static String getJsonSuccessData(JsonItem jsonItem, int totalCount){

        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success",true)
                .add("total", totalCount)
                .add("data", jsonItem.toJson());

        return toJsonString(builder.build());

    }

    public static String getJsonErrorMsg(String theErrorMessage){
        return getJsonMsg(theErrorMessage, false);
    }

    public static String getJsonSuccessMsg(String msg){
        return getJsonMsg(msg,true);
    }

    private static String getJsonMsg(String msg, boolean success){

        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", success)
                .add("msg",msg);

        return toJsonString(builder.build());

    }

    public static String toJsonString(JsonObject model) {

        final StringWriter stWriter = new StringWriter();

        try(JsonWriter jsonWriter = Json.createWriter(stWriter)){
            jsonWriter.writeObject(model);
        }

        return stWriter.toString();

    }

    protected JsonObject parseJsonObject(String jsonString){
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        return reader.readObject();
    }

    protected Integer getIntegerValue(JsonValue jsonValue){

        Integer value = null;

        switch (jsonValue.getValueType()){

            case NUMBER:
                JsonNumber num = (JsonNumber) jsonValue;
                value = num.intValue();
                break;
            case NULL:
                break;
        }

        return value;

    }

    protected JsonArray parseJsonArray(String jsonString){
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        return reader.readArray();
    }

    /**
     * Method to correctly parse Decimal data types
     */
    protected BigDecimal getBigDecimalValue(JsonValue jsonValue){

        BigDecimal value = null;

        switch (jsonValue.getValueType()){

            case NUMBER:
                JsonNumber num = (JsonNumber) jsonValue;
                value = num.bigDecimalValue();
                break;
            case NULL:
                break;
        }

        return value;
    }

}
