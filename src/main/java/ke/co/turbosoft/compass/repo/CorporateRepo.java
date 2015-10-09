package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorporateRepo extends JpaRepository<Corporate,Integer> {

    List<Corporate> findByJoinedAfter(LocalDate localDate);

    List<Corporate> findByJoinedBefore(LocalDate localDate);

    List<Corporate> findByLastUpdateAfter(LocalDateTime localDate);
}
