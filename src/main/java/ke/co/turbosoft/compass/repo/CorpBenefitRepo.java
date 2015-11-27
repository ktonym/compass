package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Category;
import ke.co.turbosoft.compass.entity.CorpBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorpBenefitRepo extends JpaRepository<CorpBenefit,Integer> {

    List<CorpBenefit> findByCategory(Category category);

    @Query("SELECT c FROM CorpBenefit c WHERE c.parentBenefit IS NULL AND c.category = :category")
    List<CorpBenefit> findMainBenefits(@Param("category") Category category);

    @Query("SELECT c FROM CorpBenefit c WHERE c.parentBenefit = :parentBenefit")
    List<CorpBenefit> findSubBenefits(@Param("parentBenefit") CorpBenefit parentBenefit);


}
