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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
    public String logon(@RequestParam(value = "j_username", required = true) String username,
                        @RequestParam(value = "j_password", required = true) String password,
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

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);


        Result<List<Menu>> ar =  menuService.findModules(user.getUsername());


        if(ar.isSuccess()){

            JsonArrayBuilder menuArrayBuilder = Json.createArrayBuilder();

            for (Menu module : ar.getData()){



               Result<List<Menu>> subAr = menuService.findItems(user.getUsername(),module);


                JsonArrayBuilder itemArrayBuilder = Json.createArrayBuilder();

               if (subAr.isSuccess())   {



                     for (Menu item: subAr.getData() ){
                         itemArrayBuilder.add(
                                 Json.createObjectBuilder()
                                         .add("idMenu", item.getId())
                                         .add("text", item.getText())
                                         .add("iconCls", item.getIconCls())
                                         .add("className", item.getClassName())
                                         .add("idParentMenu", module.getId())

                         );

                     }

//                     menuArrayBuilder.add( Json.createObjectBuilder().add(
//                             "items", itemArrayBuilder));

//               }  else {
//                   menuArrayBuilder.add(Json.createObjectBuilder().add("items", ""));
               }

                menuArrayBuilder.add(Json.createObjectBuilder()
                        .add("idMenu", module.getId())
                        .add("text", module.getText())
                        .add("iconCls", module.getIconCls())
                        .add("items", itemArrayBuilder)
                );



            }

            builder.add("data",menuArrayBuilder);


            //List<Menu> menuList = ar.getData();
            return toJsonString(builder.build());

        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

}
