package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Intermediary;
import ke.co.turbosoft.compass.entity.IntermediaryType;
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
 * Created by ktonym on 4/12/15.
 */
@Service("intermediaryService")
@Transactional
public class IntermediaryServiceImpl extends AbstractService implements IntermediaryService {

    @Autowired
    private IntermediaryRepo intermediaryRepo;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Intermediary>> findAll(String actionUsername) {

        if(!isValidUser(actionUsername))
            return ResultFactory.getFailResult(USER_INVALID);

        List<Intermediary> intermediaries = intermediaryRepo.findAll();

        return ResultFactory.getSuccessResult(intermediaries);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<Intermediary> find(Integer idIntermediary, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Intermediary> remove(Integer idIntermediary, String actionUsername) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Intermediary> store(Integer idIntermediary,
                                      String name,
                                      String pin,
                                      IntermediaryType type,
                                      LocalDate joinDate,
                                      String email,
                                      String tel,
                                      String postalAddress,
                                      String street,
                                      String town,
                                      String actionUsername) {

        if(!isValidUser(actionUsername)){
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Intermediary intermediary;
        System.out.println("Inside IntermediaryService.store() function.");
        System.out.println("ID intermediary: " + idIntermediary);
        if(idIntermediary==null || idIntermediary<1){ //new Intermediary
            System.out.println("creating a new record!");
            intermediary = new Intermediary();
        } else {
            System.out.println("updating an existing record.");
            intermediary = intermediaryRepo.findOne(idIntermediary);
        }

        intermediary.setName(name);
        intermediary.setPin(pin);
        intermediary.setType(type);
        intermediary.setJoinDate(joinDate);
        intermediary.setEmail(email);
        intermediary.setTel(tel);
        intermediary.setPostalAddress(postalAddress);
        intermediary.setStreet(street);
        intermediary.setTown(town);
       // System.out.println("About to save:"+intermediary.toString());
        intermediaryRepo.save(intermediary);


        return ResultFactory.getSuccessResult(intermediary);
    }

}
