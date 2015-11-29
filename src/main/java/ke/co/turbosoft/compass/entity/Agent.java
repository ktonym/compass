package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
@DiscriminatorValue("AGENT")
public class Agent extends Intermediary{

    public Agent() {
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        super.addJson(builder);
    }

}
