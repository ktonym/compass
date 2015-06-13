package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpBenefit;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorpBenefitService {
    List<CorpBenefit> findAll();
    void suspend(CorpBenefit corpBenefit);
    CorpBenefit add(CorpBenefit corpBenefit);
    CorpBenefit addSubBenefit(CorpBenefit mainBenefit, CorpBenefit subBenefit);
    void remove(CorpBenefit corpBenefit);
    void removeSubBenefit(CorpBenefit mainBenefit, CorpBenefit subBenefit);
    List<CorpBenefit> listSubBenefits(CorpBenefit corpBenefit);
    CorpBenefit save(CorpBenefit corpBenefit);
}
