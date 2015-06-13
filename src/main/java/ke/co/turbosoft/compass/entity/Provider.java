package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
public class Provider extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProvider;
    @Column(nullable = false,unique = true)
    private String providerName;
    private String physicalAddress;
    private String town;
    @OneToMany(mappedBy = "provider")
    private List<CoPay> coPayList;
    @OneToOne(mappedBy = "provider")
    private BankDetail bankDetail;
    @OneToMany(mappedBy = "provider")
    private List<Bill> bills;

    public Provider() {
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<CoPay> getCoPayList() {
        return coPayList;
    }

    public void setCoPayList(List<CoPay> coPayList) {
        this.coPayList = coPayList;
    }

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public Integer getId() {
        return idProvider;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idProvider",idProvider)
                .add("providerName",providerName)
                .add("physicalAddress", physicalAddress)
                .add("town",town);

    }
}
