package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Corporate extends AbstractEntity implements EntityItem<Integer> {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCorporate;
    private String corporateName;
	private String abbreviation;
	private String tel;
	private String email;
    private String postalAddress;
	@OneToMany(mappedBy = "corporate")
	private List<CorpAnniv> annivs;
    @OneToMany(mappedBy = "corporate")
    private List<ContactInfo> contactInfo;
    @Convert(converter=LocalDatePersistenceConverter.class)
    private LocalDate joined;
    @OneToMany(mappedBy = "corporate")
    private List<GroupRate> rates;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Corporate() {
        this.setJoined(LocalDate.now());
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public List<CorpAnniv> getAnnivs() {
        return annivs;
    }

    public void setAnnivs(List<CorpAnniv> annivs) {
        this.annivs = annivs;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDate getJoined() {
        return joined;
    }

    public void setJoined(LocalDate joined) {
        this.joined = joined;
    }

    public List<GroupRate> getRates() {
        return rates;
    }

    public void setRates(List<GroupRate> rates) {
        this.rates = rates;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCorporate", idCorporate)
                .add("corporateName", corporateName)
                .add("abbreviation", abbreviation)
                .add("tel",tel == null ? "" : tel)
                .add("email",email)
                .add("postalAddress", postalAddress == null ? "" : postalAddress)
                .add("joined", joined == null ? "" : DATE_FORMATTER_yyyyMMdd.format(joined));
    }

    @Override
    public Integer getId() {
        return idCorporate;
    }
}
