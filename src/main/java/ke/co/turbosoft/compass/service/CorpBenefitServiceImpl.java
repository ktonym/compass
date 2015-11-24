package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.repo.*;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akipkoech on 24/11/2015.
 */
@Service("corpBenefitService")
@Transactional
public class CorpBenefitServiceImpl extends AbstractService implements CorpBenefitService {

    @Autowired
    protected CorporateRepo corporateRepo;
    @Autowired
    protected CorpAnnivRepo corpAnnivRepo;
    @Autowired
    protected CategoryRepo categoryRepo;
    @Autowired
    protected CorpBenefitRepo corpBenefitRepo;
    @Autowired
    protected BenefitRefRepo benefitRefRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CorpBenefit> store(Integer idCategory,
                                     Integer benefitCode,
                                     Integer idCorpBenefit,
                                     Integer parentBenefit_id,
                                     MemberType memberType,
                                     Double upperLimit,
                                     String sharing,
                                     String requiresPreAuth,
                                     Integer waitingPeriod,
                                     String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        BenefitRef benefit = benefitRefRepo.getOne(benefitCode);

        if(benefit==null) return ResultFactory.getFailResult("Invalid benefit code [ "+benefitCode+" ]");

        Category cat = categoryRepo.findOne(idCategory);

        if(cat==null) return ResultFactory.getFailResult("Invalid category code [ "+idCategory+" ]");

        CorpBenefit corpBenefit;

        if(idCorpBenefit==null){ // creating a new corpBenefit
            corpBenefit = new CorpBenefit();
        } else { // modifying an existing corpBenefit
            corpBenefit = corpBenefitRepo.findOne(idCorpBenefit);
            if (corpBenefit==null) { // code supplied, but no database record found
                return ResultFactory.getFailResult("Cannot modify benefit. Invalid corporate benefit supplied [ "+idCorpBenefit+" ]");
            }
        }

        if(parentBenefit_id!=null){
            CorpBenefit parentBenefit = corpBenefitRepo.findOne(parentBenefit_id);
            if (parentBenefit==null) return ResultFactory.getFailResult("Invalid parent benefit code [ "+parentBenefit_id+" ]");
            corpBenefit.setParentBenefit(parentBenefit);
        }

        if(upperLimit==null) return ResultFactory.getFailResult("Benefit limit not specified.");

        corpBenefit.setBenefitRef(benefit);
        corpBenefit.setCategory(cat);
        corpBenefit.setMemberType(memberType);
        corpBenefit.setUpperLimit(upperLimit);
        corpBenefit.setSharing(sharing.equalsIgnoreCase("Y"));
        corpBenefit.setRequiresPreAuth(requiresPreAuth.equalsIgnoreCase("Y"));
        corpBenefit.setWaitingPeriod(waitingPeriod == null ? null : waitingPeriod);
        corpBenefitRepo.save(corpBenefit);
        return ResultFactory.getSuccessResult(corpBenefit);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<List<CorpBenefit>> store(List<Map<String, Object>> corpBenefitMaps, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        List<CorpBenefit> benefitList = new ArrayList<>();

        for (Map map : corpBenefitMaps){

            Integer idCategory = (Integer) map.get("idCategory");
            Integer benefitCode = (Integer) map.get("benefitCode");
            Integer idCorpBenefit =  (Integer) map.get("idCorpBenefit");
            Integer parentBenefit_id = (Integer) map.get("parentBenefit_id");
            MemberType memberType = MemberType.valueOf((String) map.get("memberType"));
            Double upperLimit = (Double) map.get("parentBenefit_id");
            String sharing = (String) map.get("sharing");
            String requiresPreAuth = (String) map.get("requiresPreAuth");
            Integer waitingPeriod = (Integer) map.get("waitingPeriod");

            boolean share = sharing.equalsIgnoreCase("Y");
            boolean preAuthRequired = requiresPreAuth.equalsIgnoreCase("Y");

            CorpBenefit corpBenefit = new CorpBenefit();
            corpBenefit.setUpperLimit(upperLimit);
            corpBenefit.setRequiresPreAuth(preAuthRequired);
            corpBenefit.setSharing(share);
            corpBenefit.setWaitingPeriod(waitingPeriod);
            corpBenefit.setMemberType(memberType);
            corpBenefit.setParentBenefit(corpBenefitRepo.findOne(parentBenefit_id));
            corpBenefit.setBenefitRef(benefitRefRepo.findOne(benefitCode));
            corpBenefit.setCategory(categoryRepo.findOne(idCategory));

            benefitList.add(corpBenefit);

        }

        corpBenefitRepo.save(benefitList);

        return ResultFactory.getSuccessResult(benefitList);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpBenefit>> findAll(String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CorpBenefit> remove(Integer idCorpBenefit, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<CorpBenefit> findOne(Integer idCorpBenefit, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpBenefit>> listSubBenefits(Integer idCorpBenefit, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpBenefit>> listMainBenefits(Integer idCategory, String actionUsername) {
        return null;
    }
}
