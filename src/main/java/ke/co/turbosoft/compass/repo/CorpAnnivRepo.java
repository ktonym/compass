package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorpAnnivRepo extends JpaRepository<CorpAnniv,Integer> {
	//@Query("select a from CorpAnniv a where a.corporate = :corporate")
	//List<CorpAnniv> findByCorporate(@Param("corporate") Corporate corporate);

    List<CorpAnniv> findByCorporate(Corporate corporate);

    CorpAnniv findByCorporateAndAnniv(Corporate corporate, Integer anniv);

    @Query("SELECT max(a.anniv) FROM CorpAnniv a WHERE a.corporate=:corporate")
    Integer getMax(@Param("corporate") Corporate corporate);
}
