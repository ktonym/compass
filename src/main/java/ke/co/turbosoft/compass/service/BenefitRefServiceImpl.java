package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.BenefitRef;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.repo.BenefitRefRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by akipkoech on 25/05/15.
 */
@Transactional
@Service("benefitRefService")
public class BenefitRefServiceImpl extends AbstractService implements BenefitRefService {

    @Autowired
    protected BenefitRefRepo benefitRefRepo;


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<BenefitRef> store(Integer benefitCode,
                                    String benefitName,
                                    String description,
                                    String actionUsername) {

        User actionUser = userRepo.findOne(actionUsername);

        if(actionUser==null){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        if(benefitName==null||benefitName.trim().isEmpty()){
            return ResultFactory.getFailResult("Benefit name must be non-empty");
        }

        benefitName=benefitName.trim();
        description=description.trim();

        BenefitRef benefitRef;

        // Test for an existing Benefit with a similar name
        BenefitRef testBenefitRef = benefitRefRepo.findByName(benefitName);

        if (benefitCode == null || benefitCode == 0){
            // create a new benefit
            benefitRef = new BenefitRef();
            // If the testBenefit returns anything, then we're trying to define a benefit twice!
            if (testBenefitRef!=null){
                return ResultFactory.getFailResult("Benefit ["+benefitName+"] is already in the system.");
            }
        } else {
            /**
             * something must be in the database,
             * we're therefore updating a record
              */
            benefitRef = benefitRefRepo.findOne(benefitCode);

            if(benefitRef==null){
                return ResultFactory.getFailResult("Invalid benefit code ["+benefitCode+"] supplied.");
            }
            // The update we're trying to make is for another benefit!
            if(testBenefitRef.getBenefitCode() != benefitRef.getBenefitCode()){
                return ResultFactory.getFailResult("Benefit [" + benefitName + "] already exists under a different code ");
            }
        }

        benefitRef.setBenefitName(benefitName);
        benefitRef.setDescription(description == null ? null : description);
        benefitRef.setLastUpdate(LocalDateTime.now());

        benefitRefRepo.save(benefitRef);
        return ResultFactory.getSuccessResult(benefitRef);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Result<BenefitRef> remove(Integer benefitCode, String actionUsername) {
        if(isValidUser(actionUsername)){
            /**
             * First confirm if the benefit ref has any child corporate benefits
             */

            BenefitRef testBenRef = benefitRefRepo.findOne(benefitCode);
            if(testBenRef.getCorpBenefits().isEmpty()){
                benefitRefRepo.delete(benefitCode);
                return ResultFactory.getSuccessResult("");
            } else {
                return ResultFactory.getFailResult("Benefit [" + testBenRef.getBenefitName() +"] has child records associated.");
            }

        }
        return ResultFactory.getFailResult(USER_INVALID);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<BenefitRef> find(Integer benefitCode, String actionUsername) {

        if(isValidUser(actionUsername)){
            BenefitRef benefitRef = benefitRefRepo.findOne(benefitCode);
            return ResultFactory.getSuccessResult(benefitRef);
        }
        return ResultFactory.getFailResult(USER_INVALID);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<BenefitRef>> findAll(String actionUsername) {

        if(isValidUser(actionUsername)){
            List<BenefitRef> benefitRefList = benefitRefRepo.findAll();
            return ResultFactory.getSuccessResult(benefitRefList);
        }
        return ResultFactory.getFailResult(USER_INVALID);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<BenefitRef>> search(String searchStr, String actionUsername) {

        if(isValidUser(actionUsername)){
            List<BenefitRef> benefitRefList = benefitRefRepo.findByBenefitNameLike(searchStr);
            return ResultFactory.getSuccessResult(benefitRefList);
        }
        return ResultFactory.getFailResult(USER_INVALID);
    }
}
