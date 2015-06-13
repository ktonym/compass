package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpBenefit;
import ke.co.turbosoft.compass.entity.Member;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface MemberService {
    List<Member> findAll();
    void cancel(Member member);
    List<Member> findByName(String searchStr);
    Member findByMemberNo(String searchStr);
    Boolean eligibleForTreatment(Member member);
    List<CorpBenefit> applicableBenefits(Member member);
}
