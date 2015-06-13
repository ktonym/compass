package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.ContactInfo;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.repo.ContactInfoRepo;
import ke.co.turbosoft.compass.repo.CorporateRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by akipkoech on 4/18/15.
 */
@Service("contactInfoService")
@Transactional
public class ContactInfoServiceImpl extends AbstractService implements ContactInfoService {

    @Autowired
    protected ContactInfoRepo contactInfoRepo;
    @Autowired
    protected CorporateRepo corporateRepo;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Result<ContactInfo> store(Integer idContactInfo,
                                     Integer idCorporate,
                                     String firstName,
                                     String surname,
                                     String jobTitle,
                                     String email,
                                     String tel,
                                     String actionUsername) {

        //User actionUser = userRepo.findOne(actionUsername);

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        ContactInfo contactInfo;
        ContactInfo contactInfoByEmail=contactInfoRepo.findByEmail(email);
        /**
         * check if any of <b></b>
         */

        if(idCorporate==null){
            return ResultFactory.getFailResult("Unable to store contact details without a valid corporate");
        }

        if(idContactInfo==null){
            /**
             * A new contact for this corporate
             * Need to set corporate only at creation stage. Otherwise, no changing thereafter
             */

            contactInfo = new ContactInfo();
            contactInfo.setCorporate(corporateRepo.findOne(idCorporate));
            if(contactInfoByEmail==null){
                contactInfo.setEmail(email);
            } else{
                return ResultFactory.getFailResult("Email supplied is already in use");
            }
        } else {
            /**
             * Updating a contact
             * Therefore need to check that <b>email</b> is unique
             */
                contactInfo = contactInfoRepo.findOne(idContactInfo);
                if(!contactInfoByEmail.equals(contactInfo)){
                    return ResultFactory.getFailResult("Email is in use by " +contactInfoByEmail.getFirstName().concat(" ").concat(contactInfoByEmail.getSurname()));
                }

        }

        contactInfo.setFirstName(firstName);
        contactInfo.setSurname(surname);
        contactInfo.setJobTitle(jobTitle);
        contactInfo.setTel(tel);
        contactInfoRepo.save(contactInfo);

        return ResultFactory.getSuccessResult(contactInfo);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<ContactInfo> remove(Integer idContactInfo, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        ContactInfo contactInfo = contactInfoRepo.findOne(idContactInfo);

        if(contactInfo==null){
            return ResultFactory.getFailResult("Unable to obtain contact [ " +idContactInfo+ " ] for deletion");
        }

        contactInfoRepo.delete(contactInfo);
        String msg = "Contact " + contactInfo.getFirstName().concat(" ").concat(contactInfo.getSurname()) +" was deleted by " +actionUsername;
        logger.info(msg);
        return ResultFactory.getSuccessResult(msg);

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<ContactInfo>> findAll(Integer idCorporate, String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Corporate corporate = corporateRepo.findOne(idCorporate);

        if(corporate==null){
            return ResultFactory.getFailResult("Cannot find a corporate with null ID[ " +idCorporate+ " ]");
        }

        List<ContactInfo> contactInfoList = contactInfoRepo.findByCorporate(corporate);

        return ResultFactory.getSuccessResult(contactInfoList);

    }
}
