package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by ktonym on 1/10/15.
 */
@Entity
public class BillVet extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBillVet;
    @OneToOne(optional=false)
    private Bill bill;
    @Column(nullable = false)
    private LocalDate billVetDate;
    private String narration;
    @OneToOne(mappedBy = "billVet")
    private Payment payment;
    @Column(nullable = false)
    private VetStatus vetStatus;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public BillVet() {
    }

    public Integer getIdBillVet() {
        return idBillVet;
    }

    public void setIdBillVet(Integer idBillVet) {
        this.idBillVet = idBillVet;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public LocalDate getBillVetDate() {
        return billVetDate;
    }

    public void setBillVetDate(LocalDate billVetDate) {
        this.billVetDate = billVetDate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public VetStatus getVetStatus() {
        return vetStatus;
    }

    public void setVetStatus(VetStatus vetStatus) {
        this.vetStatus = vetStatus;
    }

    @Override
    public Integer getId() {
        return idBillVet;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("idBillVet",idBillVet);
        bill.addJson(builder);
        builder.add("billVetDate",billVetDate == null ? "" : DATE_FORMATTER_yyyyMMdd.format(billVetDate))
               .add("narration",narration)
                .add("vetStatus", vetStatus.toString());

    }
}
