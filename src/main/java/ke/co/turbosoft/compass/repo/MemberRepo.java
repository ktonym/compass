package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface MemberRepo extends JpaRepository<Member,Integer> {
    List<Member>  findByPrincipal_CategoryPrincipal_Category_Anniv_Corporate(Corporate corporate);
    List<Member>  findByPrincipal_CategoryPrincipal_Category_Anniv(CorpAnniv anniv);
    List<Member>  findByPrincipal_CategoryPrincipal_Category(Category category);
    List<Member>  findByPrincipal(Principal principal);
}
