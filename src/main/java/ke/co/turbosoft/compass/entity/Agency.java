package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
@DiscriminatorValue("AGENCY")
public class Agency extends Intermediary {

    private String street;
    private String town;
    private String postalAddress;

    public Agency() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        super.addJson(builder);

        builder.add("street",street)
               .add("town",town)
               .add("postalAddress", postalAddress);
    }

}
