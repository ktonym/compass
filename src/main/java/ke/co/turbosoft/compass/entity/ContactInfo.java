package ke.co.turbosoft.compass.entity;


import javax.json.JsonObjectBuilder;
import javax.persistence.*;

/**
 * Created by akipkoech on 12/8/14.
 */

@Entity
public class ContactInfo extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idContactInfo;
    private String firstName;
    private String surname;
    private String jobTitle;
    private String email;
    private String tel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idCorporate")
    private Corporate corporate;

    public ContactInfo() {
    }

    public Integer getIdContactInfo() {
        return idContactInfo;
    }

    public void setIdContactInfo(Integer idContactInfo) {
        this.idContactInfo = idContactInfo;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }

    @Override
    public Integer getId() {
        return idContactInfo;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idContactInfo", idContactInfo)
                .add("firstName", firstName)
                .add("surname", surname)
                .add("jobTitle", jobTitle)
                .add("email", email)
                .add("tel", tel);
        corporate.addJson(builder);
    }
}
