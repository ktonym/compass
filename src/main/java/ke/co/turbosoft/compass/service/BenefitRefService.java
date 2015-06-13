package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.BenefitRef;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;

/**
 * Created by akipkoech on 25/05/15.
 */
public interface BenefitRefService {

    Result<BenefitRef> store(Integer benefitCode,
                             String benefitName,
                             String description,
                             String actionUsername);

    Result<BenefitRef> remove(Integer benefitCode,
                              String actionUsername);

    Result<BenefitRef> find(Integer benefitCode,
                            String actionUsername);

    Result<List<BenefitRef>> findAll(String actionUsername);

    Result<List<BenefitRef>> search(String searchStr,
                                    String actionUsername);

}
