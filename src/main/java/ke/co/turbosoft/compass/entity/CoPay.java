package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
public class CoPay extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCoPay;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="provider_id")
    private Provider provider;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="regulation_id")
    private Regulation regulation;
    private BigDecimal coPayAmount;

    public CoPay() {
    }

    public Integer getIdCoPay() {
        return idCoPay;
    }

    public void setIdCoPay(Integer idCoPay) {
        this.idCoPay = idCoPay;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setRegulation(Regulation regulation) {
        this.regulation = regulation;
    }

    public BigDecimal getCoPayAmount() {
        return coPayAmount;
    }

    public void setCoPayAmount(BigDecimal coPayAmount) {
        this.coPayAmount = coPayAmount;
    }

    @Override
    public Integer getId() {
        return idCoPay;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCoPay", idCoPay);
        provider.addJson(builder);
        regulation.addJson(builder);
        builder.add("coPayAmount",coPayAmount);

    }
}
