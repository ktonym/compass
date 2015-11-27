package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Category;
import ke.co.turbosoft.compass.entity.CorpAnniv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    //List<Category> findByAnniv_Corporate(Corporate corporate);
    List<Category> findByCorpAnniv(CorpAnniv corpAnniv);
    long countByCorpAnniv(CorpAnniv corpAnniv);
}
