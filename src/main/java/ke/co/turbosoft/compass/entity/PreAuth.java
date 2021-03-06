package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by ktonym on 1/10/15.
 */
@Entity
public class PreAuth extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPreAuth;
    @Convert(converter=LocalDatePersistenceConverter.class)
    @Column(nullable = false)
    private LocalDate preAuthDate;
    @Column(nullable = false)
    private String preDiagnosis;
    @Column(nullable = false)
    private BigDecimal preAuthLimit;
    @ManyToOne
    @JoinColumn(name = "provider_id",nullable = false)
    private Provider provider;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idMember",referencedColumnName = "idMember",nullable = false),
            @JoinColumn(name = "idCorpAnniv",referencedColumnName = "idCorpAnniv",nullable = false),
            @JoinColumn(name = "idCorpBenefit",referencedColumnName = "idCorpBenefit", nullable = false)
    })
    private CorpMemberBenefit corpMemberBenefit;
    @OneToMany(mappedBy="preAuth")
    private List<PreAuthBill> preAuthBills;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public PreAuth() {
    }

    public Integer getIdPreAuth() {
        return idPreAuth;
    }

    public void setIdPreAuth(Integer idPreAuth) {
        this.idPreAuth = idPreAuth;
    }

    public List<PreAuthBill> getPreAuthBills() {
        return preAuthBills;
    }

    public void setPreAuthBills(List<PreAuthBill> preAuthBills) {
        this.preAuthBills = preAuthBills;
    }

    public LocalDate getPreAuthDate() {
        return preAuthDate;
    }

    public void setPreAuthDate(LocalDate preAuthDate) {
        this.preAuthDate = preAuthDate;
    }

    public String getPreDiagnosis() {
        return preDiagnosis;
    }

    public void setPreDiagnosis(String preDiagnosis) {
        this.preDiagnosis = preDiagnosis;
    }

    public BigDecimal getPreAuthLimit() {
        return preAuthLimit;
    }

    public void setPreAuthLimit(BigDecimal preAuthLimit) {
        this.preAuthLimit = preAuthLimit;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public CorpMemberBenefit getMemberBenefit() {
        return corpMemberBenefit;
    }

    public void setMemberBenefit(CorpMemberBenefit memberBenefit) {
        this.corpMemberBenefit = memberBenefit;
    }

    @Override
    public Integer getId() {
        return idPreAuth;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("idPreAuth",idPreAuth)
                .add("preAuthDate", DATE_FORMATTER_yyyyMMdd.format(preAuthDate))
                .add("preDiagnosis",preDiagnosis)
                .add("preAuthLimit", preAuthLimit);
        provider.addJson(builder);
        corpMemberBenefit.addJson(builder);


    }
}
