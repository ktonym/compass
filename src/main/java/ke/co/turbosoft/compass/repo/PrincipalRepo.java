package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface PrincipalRepo extends JpaRepository<Principal,Integer> {
    //List<Principal> findByCategoryPrincipal_Category_Anniv_Corporate(Corporate corporate);
    //@Query("select p from Principal p where p.corporate = :corporate")
	//List<Principal> findByCorporate(@Param("corporate") Corporate corporate);
}
