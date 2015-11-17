package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/9/14.
 */
public interface PremiumRateRepo extends JpaRepository<PremiumRate,Integer> {

  //TODO create generic methods here

}
