package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.BenefitRef;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.entity.GroupRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by akipkoech on 12/9/14.
 */
public interface GroupRateRepo extends JpaRepository<GroupRate,Integer> {


}
