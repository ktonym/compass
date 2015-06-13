package ke.co.turbosoft.compass.entity;

import java.io.Serializable;

/**
 * Created by ktonym on 1/9/15.
 */
public class CorpMemberBenefitId implements Serializable{

    CorpBenefit benefit;
    Member member;

    public CorpMemberBenefitId() {
    }

	public CorpBenefit getBenefit() {
		return benefit;
	}

	public void setBenefit(CorpBenefit benefit) {
		this.benefit = benefit;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benefit == null) ? 0 : benefit.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorpMemberBenefitId other = (CorpMemberBenefitId) obj;
		if (benefit == null) {
			if (other.benefit != null)
				return false;
		} else if (!benefit.equals(other.benefit))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}

        
    
}
