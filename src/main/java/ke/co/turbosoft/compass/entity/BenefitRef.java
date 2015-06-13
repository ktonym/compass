package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by akipkoech on 25/05/15.
 */
@Entity
public class BenefitRef extends AbstractEntity implements EntityItem<Integer> {

    @Id
    private Integer benefitCode;
    @Column(unique = true)
    private String benefitName;
    private String description;
    @OneToMany(mappedBy = "benefitRef")
    private List<CorpBenefit> corpBenefits;

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

    @Override
    public Integer getId() {
        return benefitCode;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("benefitCode",benefitCode)
                .add("benefitName", benefitName)
                .add("description", description).build();
    }
}
