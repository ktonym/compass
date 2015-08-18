package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.repo.MenuRepo;
import ke.co.turbosoft.compass.repo.PermissionRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ktonym on 6/29/15.
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl extends AbstractService implements PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private MenuRepo menuRepo;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Permission>> findAll(String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        return ResultFactory.getSuccessResult(permissionRepo.findAll());

    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Permission>> findByUserGroup(Integer idUserGroup, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        UserGroup userGroup = userGroupRepo.findOne(idUserGroup);

        if(userGroup != null){

            List<Permission> permissionList = permissionRepo.findByUserGroup(userGroup);

            if(permissionList.isEmpty()){
                return ResultFactory.getFailResult("User group with ID [" + idUserGroup + "] has no permissions associated with it.");
            }
            return ResultFactory.getSuccessResult(permissionList);
        } else {
            return ResultFactory.getFailResult("No user group with ID [" + idUserGroup + "] exists.");
        }


    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Permission>> findByMenu(Integer idMenu, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        Menu menu = menuRepo.findOne(idMenu);

        if(menu != null){

            List<Permission> permissionList = permissionRepo.findByMenu(menu);

            if(permissionList.isEmpty()){
                return ResultFactory.getFailResult("Menu with ID [" + idMenu + "] has no permissions associated with it.");
            }
            return ResultFactory.getSuccessResult(permissionList);
        }   else {
            return ResultFactory.getFailResult("No menu with ID [" + idMenu + "] exists.");
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Permission> store(Integer idUserGroup, Integer idMenu, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }

        if(!isMemberOf(actionUsername,"System Administrator")) {
            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED);
        }

        UserGroup userGroup = userGroupRepo.findOne(idUserGroup);
        Menu menu = menuRepo.findOne(idMenu);

        Permission permission = permissionRepo.findByUserGroupAndMenu(userGroup, menu);

        if(permission != null){
            return ResultFactory.getFailResult("This permission already exists");
        } else {
            Permission perm = new Permission();
            perm.setMenu(menu);
            perm.setUserGroup(userGroup);
            permissionRepo.save(perm);
            return ResultFactory.getSuccessResult(perm);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<Permission> remove(Integer idUserGroup, Integer idMenu, String actionUsername) {

        if(!isValidUser(actionUsername)) {
            return ResultFactory.getFailResult(USER_INVALID);
        }
        if(!isMemberOf(actionUsername,"System Administrator")) {
            return ResultFactory.getFailResult(USER_NOT_AUTHORIZED);
        }

        UserGroup userGroup = userGroupRepo.findOne(idUserGroup);
        Menu menu = menuRepo.findOne(idMenu);

        Permission permission = permissionRepo.findByUserGroupAndMenu(userGroup, menu);

        if(permission == null){
            return ResultFactory.getFailResult("This permission does not exist");
        } else {
            permissionRepo.delete(permission);
            permissionRepo.save(permission);
        }

        return null;
    }
}
