package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.repo.BenefitRefRepo;
import ke.co.turbosoft.compass.repo.CorporateRepo;
import ke.co.turbosoft.compass.repo.GroupRateRepo;
import ke.co.turbosoft.compass.repo.PremiumInvoiceItemRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akipkoech on 17/11/2015.
 */
@Service("groupRateService")
@Transactional
public class GroupRateServiceImpl extends AbstractService implements GroupRateService {

    @Autowired
    private GroupRateRepo groupRateRepo;
    @Autowired
    private BenefitRefRepo benefitRefRepo;
    @Autowired
    private CorporateRepo corporateRepo;
    @Autowired
    private PremiumInvoiceItemRepo premiumInvoiceItemRepo;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<GroupRate> store(Integer idPremiumRate,
                                   String premiumType,
                                   Integer idCorporate,
                                   Integer benefitCode,
                                   BigDecimal upperLimit,
                                   BigDecimal premium,
                                   String familySize,
                                   LocalDateTime lastUpdate,
                                   String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Corporate corp = corporateRepo.findOne(idCorporate);
        BenefitRef benefit = benefitRefRepo.findOne(benefitCode);
        GroupRate groupRate;
        //We're querying the database to find if any defined (Corp,FamSize,Benefit,Limit) combo exists
        GroupRate testRate = groupRateRepo.findByCorporateAndFamilySizeAndBenefitRefAndUpperLimit(
                corp,familySize,benefit,upperLimit
        );

        if(idPremiumRate==null){
            /*
             * We're creating a new rate
             */

            if(testRate!=null){
                return ResultFactory.getFailResult("A similar rate exists in the system.");
            }
            /**
             * We may proceed to create a new rate
             */
            groupRate = new GroupRate();

        } else { //We're updating an existing rate
            groupRate = groupRateRepo.findOne(idPremiumRate);
            if(groupRate==null){
                return ResultFactory.getFailResult("Can't find a rate with ID ["+idPremiumRate+"].");
            }
            //TODO complete other possibilities

        }

        groupRate.setCorporate(corp);
        groupRate.setBenefit(benefit);
        groupRate.setFamilySize(familySize);
        groupRate.setPremiumType(PremiumType.valueOf(premiumType));
        groupRate.setPremium(premium);
        groupRate.setUpperLimit(upperLimit);

        groupRateRepo.save(groupRate);

        return ResultFactory.getSuccessResult(groupRate);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<List<GroupRate>> store(List<Map<String,Object>> groupRateMap, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        /**
         * Pick the first item and query for the Corporate
         */

        List<GroupRate> groupRateList = new ArrayList<>();

        Corporate corp = corporateRepo.findOne((Integer)groupRateMap.get(0).get("idCorporate"));

        for (Map map: groupRateMap){

            Integer idPremiumRate = (Integer) map.get("idPremiumRate");
            String premiumType = (String) map.get("premiumType");
            //Integer idCorporate = (Integer) map.get("idCorporate");
            Integer benefitCode = (Integer) map.get("benefitCode");
            BigDecimal upperLimit = (BigDecimal) map.get("upperLimit");
            BigDecimal premium = (BigDecimal) map.get("premium");
            String familySize = (String) map.get("familySize");

            BenefitRef benefit = benefitRefRepo.findOne(benefitCode);

            GroupRate groupRate = new GroupRate();
            groupRate.setCorporate(corp);
            groupRate.setFamilySize(familySize);
            groupRate.setPremiumType(PremiumType.valueOf(premiumType));
            groupRate.setPremium(premium);
            groupRate.setIdPremiumRate(idPremiumRate);
            groupRate.setUpperLimit(upperLimit);
            groupRate.setBenefit(benefit);

            groupRateList.add(groupRate);
        }

        groupRateRepo.save(groupRateList);
        return ResultFactory.getSuccessResult(groupRateList);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<GroupRate> remove(Integer idPremiumRate, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        GroupRate groupRate = groupRateRepo.findOne(idPremiumRate);

        if(groupRate==null){
            return ResultFactory.getFailResult("Cannot remove a null rate");
        }

        List<PremiumInvoiceItem> invoiceItems = premiumInvoiceItemRepo.findByPremiumRate(groupRate);

        if(!invoiceItems.isEmpty()){
            return ResultFactory.getFailResult("Premium Rate has premium invoices defined. Cannot delete.");
        }

        groupRateRepo.delete(groupRate);

        return ResultFactory.getSuccessResult(groupRate,"Premium rate has been deleted by " + actionUsername);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<GroupRate> find(Integer idPremiumRate, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        GroupRate groupRate = groupRateRepo.findOne(idPremiumRate);

        if(groupRate==null){
            return ResultFactory.getFailResult("No such rate found.");
        }

        return ResultFactory.getSuccessResult(groupRate);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<GroupRate>> findAll(String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        return null;
    }

    @Override
    public Result<List<GroupRate>> findByCorporate(Integer idCorporate, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Corporate corporate = corporateRepo.findOne(idCorporate);

        if(corporate==null){
            return ResultFactory.getFailResult("Invalid corporate!");
        }

        List<GroupRate> groupRates = groupRateRepo.findByCorporate(corporate);

        if(groupRates.isEmpty()){
            return ResultFactory.getFailResult("No rates found for corporate [" +corporate.toString() + "]");
        }

        return ResultFactory.getSuccessResult(groupRates);
    }
}
