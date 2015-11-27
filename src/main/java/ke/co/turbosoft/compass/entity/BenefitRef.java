package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 25/05/15.
 */
@Entity
public class BenefitRef extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer benefitCode;
    //@Column(unique = true)
    private String benefitName;
    private String description;
    @OneToMany(mappedBy = "benefitRef")
    private List<CorpBenefit> corpBenefits;
    @OneToMany(mappedBy = "benefitRef")
    private List<PremiumRate> premiumRates;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime lastUpdate;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public BenefitRef() {
    }

    public Integer getBenefitCode() {
        return benefitCode;
    }

    public void setBenefitCode(Integer benefitCode) {
        this.benefitCode = benefitCode;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CorpBenefit> getCorpBenefits() {
        return corpBenefits;
    }

    public void setCorpBenefits(List<CorpBenefit> corpBenefits) {
        this.corpBenefits = corpBenefits;
    }

    public List<PremiumRate> getPremiumRates() {
        return premiumRates;
    }

    public void setPremiumRates(List<PremiumRate> premiumRates) {
        this.premiumRates = premiumRates;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Integer getId() {
        return benefitCode;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("benefitCode",benefitCode)
                .add("benefitName", benefitName)
                .add("description", description)
                .add("lastUpdate", lastUpdate == null ? "" : DATE_FORMATTER_yyyyMMddHHmm.format(lastUpdate));
    }
}
