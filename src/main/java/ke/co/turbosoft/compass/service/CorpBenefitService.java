package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Category;
import ke.co.turbosoft.compass.entity.CorpBenefit;
import ke.co.turbosoft.compass.entity.MemberType;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorpBenefitService {

    Result<CorpBenefit> store(Integer idCategory,
                              Integer benefitCode,
                              Integer idCorpBenefit,
                              Integer parentBenefit_id,
                              MemberType memberType,
                              Double upperLimit,
                              String sharing,
                              String requiresPreAuth,
                              Integer waitingPeriod,
                              String actionUsername);

    Result<List<CorpBenefit>> store(List<Map<String,Object>> corpBenefitMap, String actionUsername);

    Result<List<CorpBenefit>> findAll(String actionUsername);

    Result<CorpBenefit> remove(Integer idCorpBenefit, String actionUsername);

    Result<CorpBenefit> findOne(Integer idCorpBenefit, String actionUsername);

    Result<List<CorpBenefit>> listSubBenefits(Integer idCorpBenefit,String actionUsername);

    Result<List<CorpBenefit>> listMainBenefits(Integer idCategory,String actionUsername);




}
