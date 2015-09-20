package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.repo.UserGroupRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ktonym on 9/20/15.
 */
@Service("userGroupService")
@Transactional
public class UserGroupServiceImpl extends AbstractService implements UserGroupService {

    @Autowired
    UserGroupRepo userGroupRepo;

    public UserGroupServiceImpl() {
        super();
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Result<List<UserGroup>> findAll(String actionUsername) {

        if(isValidUser(actionUsername)){
              return ResultFactory.getSuccessResult(userGroupRepo.findAll());
        }  else {
            return ResultFactory.getFailResult(USER_INVALID);
        }

    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Result<UserGroup> find(String groupName, String actionUsername) {
        if(isValidUser(actionUsername)){

            UserGroup userGroup = userGroupRepo.findByGroupName(groupName);

            if(userGroup == null){
                return ResultFactory.getFailResult("No such group exists");
            }

            return ResultFactory.getSuccessResult(userGroup);
        }  else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
}
