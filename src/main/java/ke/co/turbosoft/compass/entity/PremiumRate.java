package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by akipkoech on 12/9/14.
 */
//TODO setup uniqueness for (corp,benefit,upperlimit,famSize)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PREMIUM_TYPE",discriminatorType = DiscriminatorType.STRING)
public class PremiumRate extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPremiumRate;
    @Column(name = "PREMIUM_TYPE",insertable = false,updatable = false)
    private PremiumType premiumType;
    private BigDecimal upperLimit;
    private BigDecimal premium;
    private String familySize;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "benefitCode",nullable = false)
    private BenefitRef benefit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCorporate",nullable = false)
    private Corporate corporate;

    public PremiumRate() {
    }

    public Integer getIdPremiumRate() {
        return idPremiumRate;
    }

    public void setIdPremiumRate(Integer idPremiumRate) {
        this.idPremiumRate = idPremiumRate;
    }

    public PremiumType getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(PremiumType premiumType) {
        this.premiumType = premiumType;
    }

    public BenefitRef getBenefit() {
        return benefit;
    }

    public void setBenefit(BenefitRef benefit) {
        this.benefit = benefit;
    }

    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getFamilySize() {
        return familySize;
    }

    public void setFamilySize(String familySize) {
        this.familySize = familySize;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }

    @Override
    public Integer getId() {
        return idPremiumRate;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
         builder.add("idPremiumRate", idPremiumRate)
                 .add("premiumType", premiumType.toString())
                 .add("upperLimit", upperLimit)
                 .add("premium", premium)
                 .add("familySize", familySize);
        if(benefit!=null) {
            benefit.addJson(builder);
        }
        if(corporate!=null){
            corporate.addJson(builder);
        }
    }
}
