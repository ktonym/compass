package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity @IdClass(CorpMemberBenefitId.class)
public class CorpMemberBenefit extends AbstractEntity {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "idMember", referencedColumnName = "idMember", updatable = false, insertable = false),
            @JoinColumn(name = "idCorpAnniv", referencedColumnName = "idCorpAnniv", updatable = false, insertable = false)})
    private MemberAnniversary memberAnniv;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idCorpBenefit", referencedColumnName = "idCorpBenefit")
    private CorpBenefit benefit;
    private BenefitStatus status;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate wef;
    @OneToMany(mappedBy = "corpMemberBenefit")
    private List<PreAuth> preAuthList;
    @OneToOne(mappedBy = "corpMemberBenefit")
    private PremiumInvoiceItem invoiceItem;
    @OneToMany(mappedBy = "corpMemberBenefit")
    private List<Bill> bills;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public CorpMemberBenefit() {
    }

    public MemberAnniversary getMemberAnniv() {
        return memberAnniv;
    }

    public void setMemberAnniv(MemberAnniversary memberAnniv) {
        this.memberAnniv = memberAnniv;
    }

    public CorpBenefit getBenefit() {
        return benefit;
    }

    public void setBenefit(CorpBenefit benefit) {
        this.benefit = benefit;
    }

    public BenefitStatus getStatus() {
        return status;
    }

    public void setStatus(BenefitStatus status) {
        this.status = status;
    }

    public LocalDate getWef() {
        return wef;
    }

    public void setWef(LocalDate wef) {
        this.wef = wef;
    }

    public List<PreAuth> getPreAuthList() {
        return preAuthList;
    }

    public void setPreAuthList(List<PreAuth> preAuthList) {
        this.preAuthList = preAuthList;
    }

    public PremiumInvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(PremiumInvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("status",status.toString())
                .add("wef", wef == null ? "" : DATE_FORMATTER_yyyyMMdd.format(wef));
        memberAnniv.addJson(builder);
        benefit.addJson(builder);

    }
}
