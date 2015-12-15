package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.repo.*;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by akipkoech on 12/10/14.
 */
@Service("corporateService")
@Transactional
@Secured("UW_UZR")
public class CorporateServiceImpl extends AbstractService implements CorporateService {

   // private static final int PAGE_SIZE = 3;

    @Autowired
    private CorporateRepo corporateRepo;

    @Autowired
    private CorpAnnivRepo corpAnnivRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PrincipalRepo principalRepo;

    @Autowired
    private MemberRepo memberRepo;

    public CorporateServiceImpl() {
        super();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Secured("UW_UZR")
    public Result<Corporate> store(Integer idCorporate,
                                   String corporateName,
                                   String abbreviation,
                                   String tel,
                                   String email,
                                   String postalAddress,
                                   LocalDate joined,
                                   LocalDateTime lastUpdate,
                                   String actionUsername) {

       // User sessionUser = userRepo.findOne(actionUsername);

//        if(!hasRole(actionUsername,"UW_UZR")){
//            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED);
//        }

        Corporate corporate;

        if(idCorporate == null){
            corporate = new Corporate();
        } else {
            // Company exists and we're updating it
            corporate = corporateRepo.findOne(idCorporate);
            if(corporate == null){
                return ResultFactory.getFailResult("Unable to find scheme with ID = " +idCorporate);
            }
        }

        corporate.setAbbreviation(abbreviation);
        corporate.setCorporateName(corporateName);
        corporate.setEmail(email);
        corporate.setJoined(joined);
        corporate.setPostalAddress(postalAddress);
        corporate.setTel(tel);
        corporate.setLastUpdate(lastUpdate==null?LocalDateTime.now():lastUpdate);

        corporateRepo.save(corporate);

        return ResultFactory.getSuccessResult(corporate);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<List<Corporate>> store(List<Map<String,Object>> corporateMap, String actionUsername) {
        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        List<Corporate> corporateList = new ArrayList<>();


        for (Map map: corporateMap){

            Corporate corp;

            Integer idCorporate = (Integer) map.get("idCorporate");
            String corporateName = (String) map.get("corporateName");
            String abbreviation = (String) map.get("abbreviation");
            String tel = (String) map.get("tel");
            String email = (String) map.get("email");
            String postalAddress = (String) map.get("postalAddress");
            LocalDate joined = (LocalDate) map.get("joined");
            LocalDateTime lastUpdate = (LocalDateTime) map.get("lastUpdate");

            if(idCorporate==null||idCorporate==0){
                corp = new Corporate();
            } else {
                corp = corporateRepo.findOne(idCorporate);
            }


            corp.setAbbreviation(abbreviation);
            corp.setCorporateName(corporateName);
            corp.setTel(tel);
            corp.setEmail(email);
            corp.setPostalAddress(postalAddress);
            corp.setJoined(joined);
            corp.setLastUpdate(lastUpdate==null?LocalDateTime.now():lastUpdate);

            corporateList.add(corp);
        }

        corporateRepo.save(corporateList);
        return ResultFactory.getSuccessResult(corporateList);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Secured("UW_UZR")
    public Result<Corporate> remove(Integer idCorporate, String actionUsername) {

//        if(!isMemberOf(actionUsername,"System Administrator")){
//            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED);
//        }

        if(idCorporate == null){
            return ResultFactory.getFailResult("Unable to remove Corporate. Null idCorporate!");
        }

        Corporate corporate = corporateRepo.findOne(idCorporate);

        if( corporate == null){
            return ResultFactory.getFailResult("Unable to load Corporate with idCorporate=" + idCorporate +" for removal");
        } else {

            if((corporate.getAnnivs()==null || corporate.getAnnivs().isEmpty()) &&
                    (corporate.getContactInfo()==null || corporate.getContactInfo().isEmpty()) &&
                    (corporate.getRates()==null || corporate.getRates().isEmpty())){
                corporateRepo.delete(corporate);
                String msg = "Corporate " + corporate.getCorporateName()
                        + " was deleted by " + actionUsername;
                logger.info(msg);
                return ResultFactory.getSuccessResult(msg);
            } else {
                return ResultFactory.getFailResult("Corporate has child record(s) and could not be deleted");
            }

        }


    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Corporate> find(Integer idCorporate, String actionUsername) {
        if(isValidUser(actionUsername)){
            Corporate corporate=corporateRepo.findOne(idCorporate);
            return ResultFactory.getSuccessResult(corporate);
        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Corporate>> findAll(String actionUsername) {
        if(isValidUser(actionUsername)){
            List<Corporate> corporateList = corporateRepo.findAll();
            return ResultFactory.getSuccessResult(corporateList);
        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Corporate>> findModifiedAfter(LocalDateTime time, String actionUsername) {
        if(isValidUser(actionUsername)){
            List<Corporate> corporateList = corporateRepo.findByLastUpdateAfter(time);
            return ResultFactory.getSuccessResult(corporateList);
        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Corporate>> findAddedAfter(LocalDate time, String actionUsername) {
        if(isValidUser(actionUsername)){
            List<Corporate> corporateList = corporateRepo.findByJoinedAfter(time);
            return ResultFactory.getSuccessResult(corporateList);
        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Corporate>> findAddedBefore(LocalDate time, String actionUsername) {
        if(isValidUser(actionUsername)){
            List<Corporate> corporateList = corporateRepo.findByJoinedBefore(time);
            return ResultFactory.getSuccessResult(corporateList);
        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
}
