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
    @Autowired
    protected CorpMemberBenefitRepo corpMemberBenefitRepo;

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

        StringBuilder sb = new StringBuilder();

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        List<CorpBenefit> benefitList = new ArrayList<>();

        for (Map map : corpBenefitMaps){

            boolean err = false;

            CorpBenefit corpBenefit;

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
            BenefitRef benefitRef = benefitRefRepo.findOne(benefitCode);

            if (idCorpBenefit==null) {
                corpBenefit = new CorpBenefit();
            } else {
                corpBenefit = corpBenefitRepo.findOne(idCorpBenefit);
            }

            if (upperLimit!=null) {
                corpBenefit.setUpperLimit(upperLimit);
            } else {
                // escape from this loop
                sb.append("Invalid limit specified for " + corpBenefit.toString() );
                err=true;
            }
            corpBenefit.setRequiresPreAuth(preAuthRequired);
            corpBenefit.setSharing(share);
            corpBenefit.setWaitingPeriod(waitingPeriod);
            corpBenefit.setMemberType(memberType);
            if (parentBenefit_id!=null) {
                corpBenefit.setParentBenefit(corpBenefitRepo.findOne(parentBenefit_id));
            }
            if (benefitRef!=null){
                corpBenefit.setBenefitRef(benefitRef);
            } else {
                sb.append("Invalid benefit code [" + benefitCode +"] specified.");
                err=true;
            }

            corpBenefit.setCategory(categoryRepo.findOne(idCategory));

            if (!err) benefitList.add(corpBenefit);

        }

        if (benefitList.size()>0) {
            corpBenefitRepo.save(benefitList);
            return ResultFactory.getSuccessResult(benefitList,sb.toString());
        }

        return ResultFactory.getFailResult(sb.toString());

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpBenefit>> findAll(Integer idCategory, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Category cat = categoryRepo.findOne(idCategory);

        if (cat==null) {
            return ResultFactory.getFailResult("Invalid category ID ["+idCategory+"]");
        }

        List<CorpBenefit> benefitList =  corpBenefitRepo.findByCategory(cat);

        if (benefitList.isEmpty()){
            return ResultFactory.getFailResult("No benefits found for category "+cat.getCat());
        }

        return ResultFactory.getSuccessResult(benefitList);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CorpBenefit> remove(Integer idCorpBenefit, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        CorpBenefit corpBenefit = corpBenefitRepo.findOne(idCorpBenefit);

        if(corpBenefit==null){
            return ResultFactory.getFailResult("Cannot delete benefit. Invalid ID ["+idCorpBenefit+"] specified.");
        }

        if(corpBenefit.getSubBenefit().size()>0){
            return ResultFactory.getFailResult("Cannot delete benefit. Child records exist.");
        }

        List<CorpMemberBenefit> memberBenefits = corpMemberBenefitRepo.findByBenefit(corpBenefit);

        if(memberBenefits.size()>0){
            return ResultFactory.getFailResult("Cannot delete benefit. Child records exist.");
        }

        corpBenefitRepo.delete(corpBenefit);

        return ResultFactory.getSuccessResult("Benefit removed from system by " + actionUsername );
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<CorpBenefit> findOne(Integer idCorpBenefit, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        CorpBenefit benefit = corpBenefitRepo.findOne(idCorpBenefit);

        if(benefit==null){
            return ResultFactory.getFailResult("Invalid or null ID ["+idCorpBenefit+"] supplied");
        }

        return ResultFactory.getSuccessResult(benefit);
    }

}
