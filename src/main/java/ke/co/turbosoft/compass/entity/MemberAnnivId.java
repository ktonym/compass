package ke.co.turbosoft.compass.entity;

import java.io.Serializable;

/**
 * Created by akipkoech on 09/11/2015.
 */
public class MemberAnnivId implements Serializable{

    Member member;
    CorpAnniv anniv;

    public MemberAnnivId() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public CorpAnniv getAnniv() {
        return anniv;
    }

    public void setAnniv(CorpAnniv anniv) {
        this.anniv = anniv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberAnnivId)) return false;

        MemberAnnivId that = (MemberAnnivId) o;

        if (!member.equals(that.member)) return false;
        return anniv.equals(that.anniv);

    }

    @Override
    public int hashCode() {
        int result = member.hashCode();
        result = 31 * result + anniv.hashCode();
        return result;
    }
}
