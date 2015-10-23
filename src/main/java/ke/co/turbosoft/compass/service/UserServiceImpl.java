package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.entity.UserRole;
import ke.co.turbosoft.compass.repo.UserRoleRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    UserRoleRepo userRoleRepo;

    public UserServiceImpl() {
        super();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<User>> findAll(String actionUsername) {

        if ( isValidUser(actionUsername)){

            return ResultFactory.getSuccessResult(userRepo.findAll());

        } else {

            return ResultFactory.getFailResult(USER_INVALID);

        }

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<User> findByUsernameAndPassword(String username, String password) {

        User user = userRepo.findByUsernameAndPassword(username,password);

        if ( user == null ){
            return ResultFactory.getFailResult("Unable to verify username/password combination");
        } else {
            return ResultFactory.getSuccessResult(user);
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<User> store(String username, String email, String firstName, String lastName, Integer idGroup, String actionUsername) {

        //things TODO change this to query for sys_adm rights

//        if(!hasRole(actionUsername,"SYS_ADM")){
//            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED + getRoles(actionUsername).toString());
//        }

        if(username==null|| username.trim().isEmpty() || email==null || email.trim().isEmpty()){
            return ResultFactory.getFailResult("Unable to store a user without a valid non-empty username/email");
        }

//        if(password==null || password.length()==0){
//            return ResultFactory.getFailResult("Unable to store a user without a valid non-empty password");
//        }

        username=username.trim();
        email=email.trim();
        User user = userRepo.findOne(username);
        User testByEmailUser = userRepo.findByEmail(email);
        UserGroup userGroup = userGroupRepo.findOne(idGroup);

        if(user==null){

            if(testByEmailUser==null){
                user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword("1Madison#");
            } else {
                return ResultFactory.getFailResult("Unable to add new user. Email address is already in use");
            }


        } else {

            if(testByEmailUser==null){ // just changing the user's email address
                user.setEmail(email);
            } else if (!user.equals(testByEmailUser)) { // attempting to change email address to an already existing one
                return ResultFactory.getFailResult("The email address is already in use by " + testByEmailUser.getUsername()
                + " and cannot be assigned to " + username);
            }

        }

        /**
         * check whether a <b>UserGroup</b> has been defined for the User
         */
        if(userGroup==null){
            return ResultFactory.getFailResult("Cannot save a user without a group");
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserGroup(userGroup);
        //user.setPassword(password);

        userRepo.save(user);

        return ResultFactory.getSuccessResult(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<User> remove(String username, String actionUsername) {

        //User actionUser = userRepo.findOne(actionUsername);

        if(!isMemberOf(actionUsername, "SYS_ADM")){
            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED);
        }

        if(actionUsername.equalsIgnoreCase(username)){
            return ResultFactory.getFailResult("It is not allowed to delete yourself!");
        }

        if(username==null){
            return ResultFactory.getFailResult("Unable to remove null user");
        }

        User affectedUser = userRepo.findOne(username);
        long roleCount = userRoleRepo.countByUser(affectedUser);

        if(affectedUser==null){
            return ResultFactory.getFailResult("Unable to obtain user [ " +username+ " ] for deletion");
        } else if (roleCount>0){
            return ResultFactory.getFailResult("User has roles");
        } else {
            userRepo.delete(affectedUser);
            String msg = "User " + username + " deleted by " + actionUsername;
            logger.info(msg);
            return ResultFactory.getSuccessResult(msg);
        }

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<User> find(String username, String actionUsername) {
        return null;
    }

    @Override
    public Result<List<UserRole>> findUserRole(String username) {

        User user = userRepo.findOne(username);

        List<UserRole> userRoles=userRoleRepo.findByUser(user);

        if (userRoles == null || userRoles.isEmpty() || userRoles.size() == 0){
            return ResultFactory.getFailResult("There are no roles defined for user[ "+user.getFirstName()+" ]");
        } else {
            return ResultFactory.getSuccessResult(userRoles);
        }

    }
}
