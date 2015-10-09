package ke.co.turbosoft.compass.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
/**
 * TODO enable caching for lucene
 */
//@Indexed @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Corporate extends AbstractEntity implements EntityItem<Integer> {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@DocumentId
    private Integer idCorporate;
    //@Field(index = org.hibernate.search.annotations.Index.YES,analyze = Analyze.YES, store = Store.YES)
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
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime lastUpdate;
    @OneToMany(mappedBy = "corporate")
    private List<GroupRate> rates;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCorporate", idCorporate)
                .add("corporateName", corporateName)
                .add("abbreviation", abbreviation)
                .add("tel",tel == null ? "" : tel)
                .add("email",email)
                .add("postalAddress", postalAddress == null ? "" : postalAddress)
                .add("joined", joined == null ? "" : DATE_FORMATTER_yyyyMMdd.format(joined))
                .add("lastUpdate", lastUpdate == null ? "" : DATE_FORMATTER_yyyyMMddHHmm.format(lastUpdate));
    }

    @Override
    public Integer getId() {
        return idCorporate;
    }
}
