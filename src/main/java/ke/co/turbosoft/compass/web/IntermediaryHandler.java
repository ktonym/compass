package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.Intermediary;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.service.IntermediaryService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ke.co.turbosoft.compass.web.SecurityHelper.getSessionUser;

/**
 * Created by ktonym on 4/12/15.
 */
@Controller
@RequestMapping("/intermediary")
public class IntermediaryHandler extends AbstractHandler{

    @Autowired
    private IntermediaryService intermediaryService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<List<Intermediary>> ar = intermediaryService.findAll(sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    //TODO add methods for Broker, Agency, Agent (storeBroker, storeAgent, removeBroker, removeAgent)
    //TODO ensure service layer methods exist to service these requests!!




}
