package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by ktonym on 1/9/15.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"invoice_no","provider_id"})})
public class Bill extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBill;
    @Column(name="invoice_no",nullable = false)
    private String invoiceNo;
    @Column(nullable = false, unique = true)
    private String claimNo;
    @Convert(converter=LocalDatePersistenceConverter.class)
    @Column(nullable = false)
    private LocalDate invoiceDate;
    @Column(nullable = false)
    private BigDecimal invoiceAmt;
    @Column(nullable = true)
    private BigDecimal deductionAmt;
    private String deductionReason;
    @Convert(converter=LocalDatePersistenceConverter.class)
    @Column(nullable = false)
    private LocalDate enteredDate;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "member_id",referencedColumnName = "member_id",nullable = false),
            @JoinColumn(name = "benefit_id",referencedColumnName = "benefit_id",nullable = false)
    })
    private CorpMemberBenefit memberBenefit;
    @ManyToOne
    @JoinColumn(name = "provider_id",nullable = false)
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "batch_id",nullable = false)
    private ClaimBatch batch;
    @OneToOne(mappedBy = "bill")
    private BillVet billVet;
    @OneToMany(mappedBy = "bill")
    private List<PreAuthBill> preAuthBills;

    static final DateTimeFormatter DATE_FORMAT_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Bill() {
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(BigDecimal invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public BigDecimal getDeductionAmt() {
        return deductionAmt;
    }

    public void setDeductionAmt(BigDecimal deductionAmt) {
        this.deductionAmt = deductionAmt;
    }

    public String getDeductionReason() {
        return deductionReason;
    }

    public void setDeductionReason(String deductionReason) {
        this.deductionReason = deductionReason;
    }

    public LocalDate getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(LocalDate enteredDate) {
        this.enteredDate = enteredDate;
    }

    public CorpMemberBenefit getMemberBenefit() {
        return memberBenefit;
    }

    public void setMemberBenefit(CorpMemberBenefit memberBenefit) {
        this.memberBenefit = memberBenefit;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public ClaimBatch getBatch() {
        return batch;
    }

    public void setBatch(ClaimBatch batch) {
        this.batch = batch;
    }

    public BillVet getBillVet() {
        return billVet;
    }

    public void setBillVet(BillVet billVet) {
        this.billVet = billVet;
    }

	public List<PreAuthBill> getPreAuthBills() {
		return preAuthBills;
	}

	public void setPreAuthBills(List<PreAuthBill> preAuthBills) {
		this.preAuthBills = preAuthBills;
	}

    @Override
    public Integer getId() {
        return idBill;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idBill",idBill)
                .add("invoiceNo",invoiceNo)
                .add("claimNo", claimNo)
                .add("invoiceDate", invoiceDate.toString())
                .add("invoiceAmt", invoiceAmt)
                .add("deductionAmt",deductionAmt)
                .add("deductionReason",deductionReason)
                .add("enteredDate",enteredDate==null ? "" : DATE_FORMAT_yyyyMMdd.format(enteredDate));


        if(memberBenefit!=null){
            memberBenefit.addJson(builder);
        }

        batch.addJson(builder);

    }
}
