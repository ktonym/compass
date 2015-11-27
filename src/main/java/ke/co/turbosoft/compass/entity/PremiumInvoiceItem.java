package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by akipkoech on 16/11/2015.
 */
@Entity
public class PremiumInvoiceItem extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPremiumInvoiceItem;
    @Convert(converter=LocalDatePersistenceConverter.class)
    private LocalDate invoiceDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPremiumRate")
    private PremiumRate premiumRate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPremiumInvoice")
    private PremiumInvoice premiumInvoice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="idMember", referencedColumnName = "idMember", nullable = false),
                  @JoinColumn(name = "idCorpAnniv", referencedColumnName = "idCorpAnniv", nullable = false),
                  @JoinColumn(name = "idCorpBenefit",referencedColumnName = "idCorpBenefit", nullable = false)})
    private CorpMemberBenefit corpMemberBenefit;
    private BigDecimal amount;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");


    @Override
    public Integer getId() {
        return idPremiumInvoiceItem;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idPremiumInvoiceItem",idPremiumInvoiceItem)
                .add("invoiceDate", invoiceDate == null ? "" : DATE_FORMATTER_yyyyMMdd.format(invoiceDate))
                .add("amount", amount);

        if (premiumRate != null){
            premiumRate.addJson(builder);
        }

        if (corpMemberBenefit != null){
            corpMemberBenefit.addJson(builder);
        }

        if (premiumInvoice != null){
            premiumInvoice.addJson(builder);
        }

    }

    public PremiumInvoiceItem() {
    }

    public Integer getIdPremiumInvoiceItem() {
        return idPremiumInvoiceItem;
    }

    public void setIdPremiumInvoiceItem(Integer idPremiumInvoiceItem) {
        this.idPremiumInvoiceItem = idPremiumInvoiceItem;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public PremiumRate getPremiumRate() {
        return premiumRate;
    }

    public void setPremiumRate(PremiumRate premiumRate) {
        this.premiumRate = premiumRate;
    }

    public PremiumInvoice getPremiumInvoice() {
        return premiumInvoice;
    }

    public void setPremiumInvoice(PremiumInvoice premiumInvoice) {
        this.premiumInvoice = premiumInvoice;
    }

    public CorpMemberBenefit getCorpMemberBenefit() {
        return corpMemberBenefit;
    }

    public void setCorpMemberBenefit(CorpMemberBenefit corpMemberBenefit) {
        this.corpMemberBenefit = corpMemberBenefit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
