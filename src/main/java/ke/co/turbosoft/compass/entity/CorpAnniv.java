package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class CorpAnniv extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCorpAnniv;
	private Integer anniv;
	@Convert(converter=LocalDatePersistenceConverter.class)
	private LocalDate inception;
	@Convert(converter=LocalDatePersistenceConverter.class)
	private LocalDate expiry;
    @Convert(converter=LocalDatePersistenceConverter.class)
    private LocalDate renewalDate;
	@OneToMany(mappedBy = "anniv")
	private List<Category> category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="corp_id")
    private Corporate corporate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intermediary_id",nullable = true)
    private Intermediary intermediary;
    @OneToMany(mappedBy = "anniv")
    private List<MemberAnniversary> memberAnniversaries;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime lastUpdate;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public CorpAnniv() {
    }

    public Integer getIdCorpAnniv() {
        return idCorpAnniv;
    }

    public void setIdCorpAnniv(Integer idCorpAnniv) {
        this.idCorpAnniv = idCorpAnniv;
    }

    public Integer getAnniv() {
        return anniv;
    }

    public void setAnniv(Integer anniv) {
        this.anniv = anniv;
    }

    public LocalDate getInception() {
        return inception;
    }

    public void setInception(LocalDate inception) {
        this.inception = inception;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }

    public Intermediary getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(Intermediary intermediary) {
        this.intermediary = intermediary;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<MemberAnniversary> getMemberAnniversaries() {
        return memberAnniversaries;
    }

    public void setMemberAnniversaries(List<MemberAnniversary> memberAnniversaries) {
        this.memberAnniversaries = memberAnniversaries;
    }

    @Override
    public Integer getId() {
        return idCorpAnniv;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCorpAnniv", idCorpAnniv)
                .add("anniv", anniv)
                .add("inception", inception == null ? "" : DATE_FORMATTER_yyyyMMdd.format(inception))
                .add("expiry", expiry == null ? "" : DATE_FORMATTER_yyyyMMdd.format(expiry))
                .add("renewalDate", renewalDate == null ? "" : DATE_FORMATTER_yyyyMMdd.format(renewalDate))
                .add("lastUpdate", lastUpdate == null ? "" : DATE_FORMATTER_yyyyMMddHHmm.format(lastUpdate));
        if(this.getCorporate()!=null){
            corporate.addJson(builder);
        }
        if(this.getIntermediary()!= null){
            intermediary.addJson(builder);
        }
    }
}
