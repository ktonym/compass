package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by akipkoech on 09/11/2015.
 */
@Entity @IdClass(MemberAnnivId.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"idMember","idCorpAnniv"}))
public class MemberAnniversary extends AbstractEntity{

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idMember", referencedColumnName = "idMember", nullable = false)
    private Member member;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCorpAnniv", referencedColumnName = "idCorpAnniv", nullable = false)
    private CorpAnniv anniv;

    @OneToMany(mappedBy = "memberAnniv")
    private List<MemberSuspension> memberSuspensions;

    @OneToMany(mappedBy = "memberAnniv")
    private List<CorpMemberBenefit> benefits;

    public MemberAnniversary() {
    }

    public CorpAnniv getAnniv() {
        return anniv;
    }

    public void setAnniv(CorpAnniv anniv) {
        this.anniv = anniv;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<MemberSuspension> getMemberSuspensions() {
        return memberSuspensions;
    }

    public void setMemberSuspensions(List<MemberSuspension> memberSuspensions) {
        this.memberSuspensions = memberSuspensions;
    }

    public List<CorpMemberBenefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<CorpMemberBenefit> benefits) {
        this.benefits = benefits;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        member.addJson(builder);
        anniv.addJson(builder);
    }
}
