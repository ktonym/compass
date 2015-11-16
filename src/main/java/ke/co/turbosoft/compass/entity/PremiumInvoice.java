package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 16/11/2015.
 */
@Entity
public class PremiumInvoice extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPremiumInvoice;
    @Column(unique = true,nullable = false)
    private String invoiceNumber;
    private BusinessClass businessClass;
    @OneToMany(mappedBy = "premiumInvoice")
    private List<PremiumInvoiceItem> invoiceItems;
    @Convert(converter=LocalDatePersistenceConverter.class)
    private LocalDate invoiceDate;
    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public Integer getId() {
        return idPremiumInvoice;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idPremiumInvoice",idPremiumInvoice)
                .add("invoiceNumber", invoiceNumber)
                .add("businessClass",businessClass.toString())
                .add("invoiceDate",invoiceDate == null ? "" : DATE_FORMATTER_yyyyMMdd.format(invoiceDate));
    }

    public PremiumInvoice() {
    }

    public Integer getIdPremiumInvoice() {
        return idPremiumInvoice;
    }

    public void setIdPremiumInvoice(Integer idPremiumInvoice) {
        this.idPremiumInvoice = idPremiumInvoice;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BusinessClass getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(BusinessClass businessClass) {
        this.businessClass = businessClass;
    }

    public List<PremiumInvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<PremiumInvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
