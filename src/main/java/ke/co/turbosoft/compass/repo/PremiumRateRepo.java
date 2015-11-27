package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.BenefitRef;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.entity.GroupRate;
import ke.co.turbosoft.compass.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by akipkoech on 12/9/14.
 */
public interface PremiumRateRepo extends JpaRepository<PremiumRate,Integer> {

    PremiumRate findByCorporateAndFamilySizeAndBenefitRefAndUpperLimit(Corporate corporate,
                                                                     String famSize,
                                                                     BenefitRef benefitRef,
                                                                     BigDecimal upperLimit);

    List<PremiumRate> findByCorporate(Corporate corporate);

  //TODO create generic methods here

}
