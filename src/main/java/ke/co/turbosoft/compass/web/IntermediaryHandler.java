package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.Intermediary;
import ke.co.turbosoft.compass.entity.IntermediaryType;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.service.IntermediaryService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    static final DateTimeFormatter DATE_FORMAT_yyyyMMdd =  DateTimeFormatter.ofPattern("yyyyMMdd");

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

    @RequestMapping(value = "/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String store(
            @RequestParam(value = "data",required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        JsonObject jsonObj = parseJsonObject(jsonData);

        String joinDateVal = jsonObj.getString("joinDate");
        IntermediaryType intType = IntermediaryType.valueOf(jsonObj.getString("type"));

        //System.out.println(jsonObj.getString("idIntermediary"));
        Integer idInt = Integer.valueOf(jsonObj.getString("idIntermediary"));
        Result<Intermediary> ar = intermediaryService.store(
                                idInt,
                                jsonObj.getString("name"),
                                jsonObj.getString("pin"),
                                intType,
                                LocalDate.parse(joinDateVal, DATE_FORMAT_yyyyMMdd),
                                jsonObj.getString("email"),
                                jsonObj.getString("tel"),
                                jsonObj.getString("postalAddress"),
                                jsonObj.getString("street"),
                                jsonObj.getString("town"),
                                sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }




    //TODO add methods for Broker, Agency, Agent (storeBroker, storeAgent, removeBroker, removeAgent)
    //TODO ensure service layer methods exist to service these requests!!




}
