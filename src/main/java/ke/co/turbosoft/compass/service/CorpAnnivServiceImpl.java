package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.repo.CategoryRepo;
import ke.co.turbosoft.compass.repo.CorpAnnivRepo;
import ke.co.turbosoft.compass.repo.CorporateRepo;
import ke.co.turbosoft.compass.repo.IntermediaryRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by akipkoech on 4/6/15.
 */
@Transactional
@Service("corpAnnivService")
public class CorpAnnivServiceImpl extends AbstractService implements CorpAnnivService {

    @Autowired
    protected CorporateRepo corporateRepo;
    @Autowired
    protected CorpAnnivRepo corpAnnivRepo;
    @Autowired
    protected IntermediaryRepo intermediaryRepo;
    @Autowired
    protected CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CorpAnniv> store(Integer idCorporate,
                                   Integer idCorpAnniv,
                                   Integer idIntermediary,
                                   Integer anniv,
                                   LocalDate startDate,
                                   LocalDate endDate,
                                   LocalDate renewalDate,
                                   String actionUsername) {

        User actionUser = userRepo.findOne(actionUsername);

        if(actionUser == null){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Corporate corporate = corporateRepo.findOne(idCorporate);

        if(corporate==null){
            return ResultFactory.getFailResult("Cannot create a cover period without a scheme.");
        }

        if(startDate==null){
            return ResultFactory.getFailResult("Cannot create a cover period without a valid start date.");
        } else {
            if(endDate==null){
                return ResultFactory.getFailResult("Cannot create a cover period without a valid end date.");
            } else {
                if(endDate.isBefore(startDate)){
                    return ResultFactory.getFailResult("End date cannot predate start date.");
                }
                //if(Period.between(startDate,endDate).getDays()>)
            }
        }

        if(startDate.plusYears(1).isBefore(endDate)){
            return ResultFactory.getFailResult("Policy cannot run more than a year.");
        }

        if(renewalDate.isBefore(endDate)){
            return ResultFactory.getFailResult("Policy cannot be renewed before the preceding cover lapses");
        }

        CorpAnniv testCorpAnniv = corpAnnivRepo.findByCorporateAndAnniv(corporateRepo.findOne(idCorporate),anniv);

        CorpAnniv corpAnniv;

        /**
         *  Checking if we're creating a new anniversary or not
         */

        if(idCorpAnniv == null){

            if(testCorpAnniv != null){

                return ResultFactory.getFailResult("Unable to add new cover period: ["+ anniv +"]the anniversary already exists");
            }

                corpAnniv = new CorpAnniv();
                corpAnniv.setAnniv(anniv);
                corpAnniv.setCorporate(corporateRepo.findOne(idCorporate));

        } else { // We're updating an existing anniversary.

            corpAnniv = corpAnnivRepo.findOne(idCorpAnniv); // Retrieve from the database based on idCorpAnniv

            if(corpAnniv == null){

                return ResultFactory.getFailResult("Cannot update the cover period: Anniversary given doesn't exist!");

            } else {

                /**
                 * No (anniv,corpId) combination exists,
                 * Safe to update the new cover period
                 */

                if(testCorpAnniv == null){
                    corpAnniv.setAnniv(anniv);
                    corpAnniv.setCorporate(corporateRepo.findOne(idCorporate));
                }
            }

        }

        corpAnniv.setStartDate(startDate);
        corpAnniv.setEndDate(endDate);
        corpAnniv.setRenewalDate(LocalDate.now().plusYears(1));
        corpAnniv.setIntermediary(intermediaryRepo.findOne(idIntermediary));

        corpAnnivRepo.save(corpAnniv);

        return ResultFactory.getSuccessResult(corpAnniv);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<CorpAnniv> remove(Integer idCorpAnniv, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }


        if(idCorpAnniv == null ){
            return ResultFactory.getFailResult("Unable to remove null cover period");
        }

        CorpAnniv corpAnniv = corpAnnivRepo.findOne(idCorpAnniv);
        long categoryCount = categoryRepo.countByAnniv(corpAnniv);

        if(corpAnniv==null){
            return ResultFactory.getFailResult("Unable to obtain cover period by ID [ " +idCorpAnniv + " ] for deletion");
        } else if(categoryCount>0){
            return ResultFactory.getFailResult("Cover period has categories defined. Cannot delete.");
        } else {

            corpAnnivRepo.delete(corpAnniv);
            String msg = "Cover period " + corpAnniv.getAnniv() + " was deleted by " + actionUsername;
            logger.info(msg);
            return ResultFactory.getSuccessResult(msg);

        }


    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<CorpAnniv> find(Integer idCorpAnniv, String actionUsername) {

        if(isValidUser(actionUsername)){

            CorpAnniv corpAnniv = corpAnnivRepo.findOne(idCorpAnniv);
            return ResultFactory.getSuccessResult(corpAnniv);

        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpAnniv>> findAll(String actionUsername) {

        if(isValidUser(actionUsername)){

            return ResultFactory.getSuccessResult(corpAnnivRepo.findAll());

        } else {

            return ResultFactory.getFailResult(USER_INVALID);

        }

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<CorpAnniv>> findByCorporate(Corporate corporate, String actionUsername) {
        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }
        List<CorpAnniv> corpAnnivList = corpAnnivRepo.findByCorporate(corporate);
        return ResultFactory.getSuccessResult(corpAnnivList);
    }
}
