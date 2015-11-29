package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.BenefitRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by akipkoech on 25/05/15.
 */
public interface BenefitRefRepo extends JpaRepository<BenefitRef, Integer> {
    BenefitRef findByBenefitName(String benefitName);
    @Query("SELECT b FROM BenefitRef b WHERE UPPER(b.benefitName)= UPPER(:name)")
    BenefitRef findByName(@Param("name")String name);
    List<BenefitRef> findByBenefitNameLike(String searchStr);
    List<BenefitRef> findByLastUpdateAfter(LocalDateTime mTime);
}
