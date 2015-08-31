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
    public Result<List<Menu>> findModules(String actionUsername) {

        if(isValidUser(actionUsername)){
            // TODO ensure we return only modules belonging to actionUser;

             List<Menu> moduleList = menuRepo.findModulesFromUser(actionUsername);

             if(moduleList.isEmpty()) {
                return ResultFactory.getFailResult("User ["+actionUsername+"] has no roles assigned yet. Contact system administrator");
             }
             return ResultFactory.getSuccessResult(moduleList);
        }   else {
             return ResultFactory.getFailResult(USER_INVALID);
        }



    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<List<Menu>> findItems(String actionUsername, int parentMenu){

        if(isValidUser(actionUsername)){

            List<Menu> itemList = menuRepo.findItemsFromUserAndModule(actionUsername,parentMenu);
            if (itemList.isEmpty()) {
               return ResultFactory.getFailResult("No menus found for user ["+actionUsername+"]");
            }
            return ResultFactory.getSuccessResult(itemList);

        } else {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
}
