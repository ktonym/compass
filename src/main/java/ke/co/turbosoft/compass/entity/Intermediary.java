package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="INTERMEDIARY_TYPE",discriminatorType = DiscriminatorType.STRING)
//TODO consider replacing above @nnotation with   @DiscriminatorFormula
//TODO read more about @ForceDiscriminator
public class Intermediary extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idIntermediary;
    private String PIN;
   // @Column(name = "INTERMEDIARY_TYPE",insertable = false,updatable = false)
    private IntermediaryType type;
    private String name;
    private String street;
    private String town;
    private String postalAddress;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate joinDate;
    private String email;
    private String tel;
    @OneToMany(mappedBy = "intermediary")
    private List<CorpAnniv> corpAnnivs;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Intermediary() {
    }

    public Integer getIdIntermediary() {
        return idIntermediary;
    }

    public void setIdIntermediary(Integer idIntermediary) {
        this.idIntermediary = idIntermediary;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public IntermediaryType getType() {
        return type;
    }

    public void setType(IntermediaryType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
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

    public List<CorpAnniv> getCorpAnnivs() {
        return corpAnnivs;
    }

    public void setCorpAnnivs(List<CorpAnniv> corpAnnivs) {
        this.corpAnnivs = corpAnnivs;
    }

    @Override
    public Integer getId() {
        return idIntermediary;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idIntermediary", idIntermediary)
                .add("PIN", PIN)
                .add("type",type.toString())
                .add("name", name)
                .add("street",street)
                .add("town", town)
                .add("postalAddress", postalAddress)
                .add("joinDate", joinDate == null ? "" : DATE_FORMATTER_yyyyMMdd.format(joinDate))
                .add("email",email)
                .add("tel",tel);
    }



}
