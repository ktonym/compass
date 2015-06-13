package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ktonym on 1/9/15.
 */
@Entity
public class BankDetail extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBankDetail;
    private String bankName;
    private String branch;
    @Column(nullable = false, unique = true)
    private int accountNo;
    @OneToOne(optional=false)
    // TODO consider using primaryKeyJoinColumn here!!
   // @PrimaryKeyJoinColumn
    private Provider provider;
    @OneToMany(mappedBy = "bankDetail")
    private List<Payment> payments;

    public BankDetail() {
    }

    public Integer getIdBankDetail() {
        return idBankDetail;
    }

    public void setIdBankDetail(Integer idBankDetail) {
        this.idBankDetail = idBankDetail;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public Integer getId() {
        return idBankDetail;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idBankDetail", idBankDetail)
                .add("bankName",bankName)
                .add("branch", branch)
                .add("accountNo", accountNo);

        provider.addJson(builder);
    }
}
