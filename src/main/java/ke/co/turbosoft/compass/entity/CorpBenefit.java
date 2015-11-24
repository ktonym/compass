package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Entity implementation class for Entity: CorpBenefit
 *
 */
@Entity

public class CorpBenefit extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCorpBenefit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "benefitCode", nullable = false)
	private BenefitRef benefitRef;
	private Double upperLimit;
    @Enumerated(EnumType.STRING)
	private MemberType memberType;
	private boolean sharing;
    private boolean requiresPreAuth;
    private Integer waitingPeriod;
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parentBenefit_id",nullable = true)
	private CorpBenefit parentBenefit;
	@OneToMany(mappedBy = "parentBenefit")
	private List<CorpBenefit> subBenefit;
    @OneToMany(mappedBy = "benefit")
    private List<CorpMemberBenefit> corpMemberBenefits;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
//    @OneToMany(mappedBy = "corpBenefit")
//    private  List<PremiumRate> premiumRates;


    public CorpBenefit() {
    }

    public Integer getIdCorpBenefit() {
        return idCorpBenefit;
    }

    public void setIdCorpBenefit(Integer idCorpBenefit) {
        this.idCorpBenefit = idCorpBenefit;
    }

    public BenefitRef getBenefitRef() {
        return benefitRef;
    }

    public void setBenefitRef(BenefitRef benefitRef) {
        this.benefitRef = benefitRef;
    }

    public Double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public boolean isSharing() {
        return sharing;
    }

    public void setSharing(boolean sharing) {
        this.sharing = sharing;
    }

    public boolean isRequiresPreAuth() {
        return requiresPreAuth;
    }

    public void setRequiresPreAuth(boolean requiresPreAuth) {
        this.requiresPreAuth = requiresPreAuth;
    }

    public Integer getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(Integer waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public CorpBenefit getParentBenefit() {
        return parentBenefit;
    }

    public void setParentBenefit(CorpBenefit parentBenefit) {
        this.parentBenefit = parentBenefit;
    }

    public List<CorpBenefit> getSubBenefit() {
        return subBenefit;
    }

    public void setSubBenefit(List<CorpBenefit> subBenefit) {
        this.subBenefit = subBenefit;
    }

    public List<CorpMemberBenefit> getCorpMemberBenefits() {
        return corpMemberBenefits;
    }

    public void setCorpMemberBenefits(List<CorpMemberBenefit> corpMemberBenefits) {
        this.corpMemberBenefits = corpMemberBenefits;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public List<PremiumRate> getPremiumRates() {
//        return premiumRates;
//    }
//
//    public void setPremiumRates(List<PremiumRate> premiumRates) {
//        this.premiumRates = premiumRates;
//    }

    public boolean isMainBenefit(){
        return (this.getParentBenefit() == null);
    }

    @Override
    public Integer getId() {
        return idCorpBenefit;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCorpBenefit",idCorpBenefit)
                .add("upperLimit",upperLimit)
                .add("memberType",memberType.toString())
                .add("sharing",sharing)
                .add("requiresPreAuth",requiresPreAuth)
                .add("waitingPeriod", waitingPeriod);
        parentBenefit.addJson(builder);
        category.addJson(builder);
        benefitRef.addJson(builder);
    }
}
