package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ktonym on 1/9/15.
 */
@Entity
public class ClaimBatch extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idClaimBatch;
    @Column(nullable = false, unique = true)
    private String batchNo;
    @ManyToOne
    @JoinColumn(name="voucher_id",nullable = false)
    private PaymentVoucher voucher;
    @OneToMany(mappedBy = "batch")
    private List<Bill> bills;

    public ClaimBatch() {
    }

    public Integer getIdClaimBatch() {
        return idClaimBatch;
    }

    public void setIdClaimBatch(Integer idClaimBatch) {
        this.idClaimBatch = idClaimBatch;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public PaymentVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(PaymentVoucher voucher) {
        this.voucher = voucher;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public Integer getId() {
        return idClaimBatch;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("idClaimBatch",idClaimBatch)
                .add("batchNo",batchNo);
        voucher.addJson(builder);

    }
}
