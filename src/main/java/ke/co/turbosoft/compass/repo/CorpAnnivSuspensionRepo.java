package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.CorpAnnivSuspension;
import ke.co.turbosoft.compass.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 31/10/15.
 */
public interface CorpAnnivSuspensionRepo extends JpaRepository<CorpAnnivSuspension, Integer> {

    List<CorpAnnivSuspension> findByCorpAnniv(CorpAnniv corpAnniv);
    List<CorpAnnivSuspension> findByCorpAnniv_Corporate(Corporate corporate);

}
