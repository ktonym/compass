package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Intermediary;
import ke.co.turbosoft.compass.entity.IntermediaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by akipkoech on 4/7/15.
 */
public interface IntermediaryRepo extends JpaRepository<Intermediary,Integer> {

    List<Intermediary> findByType(IntermediaryType intermediaryType);
    List<Intermediary> findByJoinDateAfter(LocalDate joinDate);
    List<Intermediary> findByJoinDateBefore(LocalDate joinDate);
    List<Intermediary> findByNameLike(String searchStr);

}
