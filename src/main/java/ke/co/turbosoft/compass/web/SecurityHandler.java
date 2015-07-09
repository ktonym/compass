package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.service.MenuService;
import ke.co.turbosoft.compass.service.UserService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static ke.co.turbosoft.compass.web.SecurityHelper.SESSION_ATTRIB_USER;

/**
 * Created by ktonym on 1/13/15.
 */
@Controller
@RequestMapping("/security")
public class SecurityHandler  extends AbstractHandler{

    @Autowired
    protected UserService userService;

    @Autowired
    protected MenuService menuService;

//    @Autowired
//    protected UserRoleService userRoleService;

    @RequestMapping(value = "/logon", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String logon(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletRequest request){

        Result<User> ar = userService.findByUsernameAndPassword(username,password);

        if(ar.isSuccess()){
            User user = ar.getData();
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_ATTRIB_USER, user);
            return getJsonSuccessData(user);
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/logout", produces = {"application/json"})
    @ResponseBody
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        session.removeAttribute(SESSION_ATTRIB_USER);
        return getJsonSuccessMsg("User logged out...");
    }

    @RequestMapping(value = "/menu/findAll", method = RequestMethod.GET, produces = "{application/json}")
    @ResponseBody
    public String getMenu(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_ATTRIB_USER);

        Result<List<Menu>> ar =  menuService.findAll(user.getUsername());

        if(ar.isSuccess()){
            List<Menu> menuList = ar.getData();
            return getJsonSuccessData(menuList);
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

}
