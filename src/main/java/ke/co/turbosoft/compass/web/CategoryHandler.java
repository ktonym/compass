package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.Category;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.service.CategoryService;
import ke.co.turbosoft.compass.service.CorpAnnivService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ke.co.turbosoft.compass.web.SecurityHelper.getSessionUser;

/**
 * Created by akipkoech on 4/13/15.
 */
@Controller
@RequestMapping("/category")
public class CategoryHandler extends AbstractHandler{

    @Autowired
    private CorpAnnivService corpAnnivService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/findByAnniv", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findByAnniv(
            @RequestParam(value = "idCorpAnniv", required = true) Integer idCorpAnniv,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<List<Category>> ar = categoryService.findByAnniv(corpAnnivService.find(idCorpAnniv, sessionUser.getUsername()).getData(), sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String store(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request
    ){

        User sessionUser = getSessionUser(request);


        JsonObject jsonObj = parseJsonObject(jsonData);

        Result<Category> ar = categoryService.store(
                getIntegerValue(jsonObj.get("idCategory")),
                getIntegerValue(jsonObj.get("idCorpAnniv")),
                jsonObj.getString("cat").charAt(0),
                jsonObj.getString("description"),
                sessionUser.getUsername());

        if (ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String remove(
            @RequestParam(value = "idCategory", required = true) Integer idCategory,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<Category> ar = categoryService.remove(idCategory, sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value="/principal/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String storePrincipal(
            @RequestParam(value="data", required = true) String jsonData,
            HttpServletRequest request){

        //TODO populate member by default and complete setup

        return null;
    }

    @RequestMapping(value="/principal/findByCategory", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String findPrincipal(
            @RequestParam(value="idCategory", required = true) Integer idCategory,
            HttpServletRequest request){
        //TODO complete setup
        return null;
    }

    @RequestMapping(value="/principal/remove", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String removePrincipal(
            @RequestParam(value="idPrincipal", required = true) Integer idPrincipal,
            HttpServletRequest request){
        //TODO complete setup
        return null;
    }

}
