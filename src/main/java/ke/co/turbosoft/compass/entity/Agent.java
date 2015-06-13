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

    private String firstName;
    private String surname;
    private String otherNames;

    public Agent() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        super.addJson(builder);
        builder.add("firstName", firstName)
                .add("surname",surname)
                .add("otherNames",otherNames);

    }

}
