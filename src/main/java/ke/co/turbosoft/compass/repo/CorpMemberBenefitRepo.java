package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.CorpBenefit;
import ke.co.turbosoft.compass.entity.CorpMemberBenefit;
import ke.co.turbosoft.compass.entity.CorpMemberBenefitId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorpMemberBenefitRepo extends JpaRepository<CorpMemberBenefit,CorpMemberBenefitId> {

    List<CorpMemberBenefit> findByBenefit(CorpBenefit benefit);
}
