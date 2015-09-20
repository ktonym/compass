package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.service.UserGroupService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ke.co.turbosoft.compass.web.SecurityHelper.getSessionUser;

/**
 * Created by ktonym on 9/20/15.
 */
@Controller
@RequestMapping("/group")
public class UserGroupHandler extends AbstractHandler {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String find(@RequestParam(value = "name") String groupname,
                       HttpServletRequest request){
        User sessionUser = getSessionUser(request);

        Result<UserGroup> ar = userGroupService.find(groupname,sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request) {

        User sessionUser = getSessionUser(request);

        Result<List<UserGroup>> ar = userGroupService.findAll(sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }


    }


}
