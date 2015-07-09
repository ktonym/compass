package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.entity.UserRole;
import ke.co.turbosoft.compass.repo.UserGroupRepo;
import ke.co.turbosoft.compass.repo.UserRepo;
import ke.co.turbosoft.compass.repo.UserRoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by akipkoech on 3/5/15.
 */
public abstract class AbstractService {

    final protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UserRepo userRepo;

//    @Autowired
//    protected UserRoleRepo userRoleRepo;
//
    @Autowired
    protected UserGroupRepo userGroupRepo;

    protected final String USER_INVALID = "Not a valid user";
    protected final String USER_NOT_AUTHORIZED = "Transaction not authorized";

    protected boolean isValidUser(String username){
        User user = userRepo.findOne(username);
        return user != null;
    }

//    protected boolean hasRole(String username, String roleName){
//
//        Predicate<UserRole> predicate = p -> p.getRole().getRoleName().equals(roleName);
//
//        return getRoles(username).stream().anyMatch(predicate);
//    }
//
//    protected List<UserRole> getRoles(String username){
//
//        User user = userRepo.findOne(username);
//
//        return userRoleRepo.findByUser(user);
//    }

    protected boolean isMemberOf(String username, String userGroupName){

        UserGroup userGroup = userGroupRepo.findByGroupName(userGroupName);

        return userRepo.findOne(username).getUserGroup().equals(userGroup);
    }

}
