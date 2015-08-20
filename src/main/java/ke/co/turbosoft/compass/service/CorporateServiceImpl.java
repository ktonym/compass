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
import java.util.List;

/**
 * Created by akipkoech on 12/10/14.
 */
@Service("corporateService")
@Transactional
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

        corporateRepo.save(corporate);

        return ResultFactory.getSuccessResult(corporate);

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
}
