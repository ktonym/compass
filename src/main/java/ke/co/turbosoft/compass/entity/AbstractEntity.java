package ke.co.turbosoft.compass.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;

/**
 * Created by akipkoech on 12/8/14.
 */

public abstract class AbstractEntity implements JsonItem,Serializable{

    @Override
    public JsonObject toJson(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        addJson(builder);
        return builder.build();
    }


}
