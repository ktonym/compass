package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity @IdClass(CorpMemberBenefitId.class)
public class CorpMemberBenefit extends AbstractEntity {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="member_id")
    private Member member;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="benefit_id")
    private CorpBenefit benefit;
    private BenefitStatus status;
    private LocalDate wef;
    @OneToMany
    private List<PreAuth> preAuthList;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public CorpMemberBenefit() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public CorpBenefit getBenefit() {
        return benefit;
    }

    public void setBenefit(CorpBenefit benefit) {
        this.benefit = benefit;
    }

    public BenefitStatus getStatus() {
        return status;
    }

    public void setStatus(BenefitStatus status) {
        this.status = status;
    }

    public LocalDate getWef() {
        return wef;
    }

    public void setWef(LocalDate wef) {
        this.wef = wef;
    }

    public List<PreAuth> getPreAuthList() {
        return preAuthList;
    }

    public void setPreAuthList(List<PreAuth> preAuthList) {
        this.preAuthList = preAuthList;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("status",status.toString())
                .add("wef", wef == null ? "" : DATE_FORMATTER_yyyyMMdd.format(wef));
        member.addJson(builder);
        benefit.addJson(builder);

    }
}
