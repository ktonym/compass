package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.repo.MenuRepo;
import ke.co.turbosoft.compass.repo.UserGroupRepo;
import ke.co.turbosoft.compass.repo.UserRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl extends AbstractService implements MenuService {

    @Autowired
    MenuRepo menuRepo;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Result<List<Menu>> findAll(String actionUsername) {

        if(isValidUser(actionUsername)){
            // TODO ensure we return only menus belonging to actionUser;

             List<Menu> moduleList = menuRepo.findModulesFromUser(actionUsername);

             if(moduleList.isEmpty()) {
                return ResultFactory.getFailResult("User ["+actionUsername+"] has no roles assigned yet. Contact system administrator");
             }
             return ResultFactory.getSuccessResult(moduleList);
        }   else {
             return ResultFactory.getFailResult(USER_INVALID);
        }



    }
}
