package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.service.CategoryService;
import ke.co.turbosoft.compass.service.ContactInfoService;
import ke.co.turbosoft.compass.service.CorpAnnivService;
import ke.co.turbosoft.compass.service.CorporateService;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ke.co.turbosoft.compass.web.SecurityHelper.getSessionUser;

/**
 * Created by akipkoech on 12/10/14.
 */
@Controller
@RequestMapping("/uw")
public class CorporateHandler extends AbstractHandler{

    @Autowired
    private CorporateService corporateService;

    @Autowired
    private CorpAnnivService corpAnnivService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContactInfoService contactInfoService;

    static final DateTimeFormatter DATE_FORMAT_yyyyMMdd =  DateTimeFormatter.ofPattern("yyyyMMdd");
//    @ModelAttribute("corporate")
//    public Corporate create(){
//        return new Corporate();
//    }

    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.registerCustomEditor(LocalDate.class,new LocalDateEditor(DATE_FORMAT_yyyyMMdd,true));

    }

    @RequestMapping(value="/corporate/findAll",method = RequestMethod.GET, produces={"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<List<Corporate>> ar = corporateService.findAll(sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }


    @RequestMapping(value = "/corporate/find", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String find (
            @RequestParam(value = "idCorporate", required = true) Integer idCorporate,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<Corporate> ar = corporateService.find(idCorporate,sessionUser.getUsername());

        if(ar.isSuccess()){

            return getJsonSuccessData(ar.getData());

        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value="/corporate/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String store (
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        JsonObject jsonObj = parseJsonObject(jsonData);

//        String dateVal = null;
//
//           if(!jsonObj.getString("joined").isEmpty() && jsonObj.getString("joined").length()>0){
//                dateVal = jsonObj.getString("joined");
//           }
        String dateVal = jsonObj.getString("joined");

        Result<Corporate> ar = corporateService.store(
                getIntegerValue(jsonObj.get("idCorporate")),
                jsonObj.getString("corporateName"),
                jsonObj.getString("abbreviation"),
                jsonObj.getString("tel"),
                jsonObj.getString("email"),
                jsonObj.getString("postalAddress"),
                LocalDate.parse(dateVal, DATE_FORMAT_yyyyMMdd),
                sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/corporate/treenode", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String getCorpTreeNode(
            @RequestParam(value = "node", required = true) String node,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        logger.info(node);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        JsonArrayBuilder childrenArrayBuilder = Json.createArrayBuilder();

        if(node.equals("root")){

            Result<List<Corporate>> ar = corporateService.findAll(sessionUser.getUsername());

            if(ar.isSuccess()){

                for(Corporate corporate: ar.getData()){

                    List<CorpAnniv> annivs = corpAnnivService.findByCorporate(corporate,sessionUser.getUsername()).getData();

                    childrenArrayBuilder.add(
                            Json.createObjectBuilder().add("id", getTreeNodeId(corporate))
                                                      .add("text", corporate.getCorporateName())
                                                      .add("leaf", annivs.isEmpty())
                    );

                }

            } else {

                return getJsonErrorMsg(ar.getMsg());

            }

        } else if ( node.startsWith("S")){

            String[] idSplit = node.split("_");
            int idCorporate = Integer.parseInt(idSplit[1]);
            Result<Corporate> ar = corporateService.find(idCorporate, sessionUser.getUsername());
            List<CorpAnniv> annivs = corpAnnivService.findByCorporate(ar.getData(),sessionUser.getUsername()).getData();

            for(CorpAnniv anniv: annivs){
                //TODO complete creation of this builder
                List<Category> cats = categoryService.findByAnniv(anniv, sessionUser.getUsername()).getData();

                childrenArrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("id", getTreeNodeId(anniv))
                                .add("text", anniv.getAnniv())
                                .add("leaf", cats.isEmpty())
                );


            }


        } else if ( node.startsWith("A")){

            String[] idSplit = node.split("_");
            int idCorpAnniv = Integer.parseInt(idSplit[1]);
            Result<CorpAnniv> ar = corpAnnivService.find(idCorpAnniv, sessionUser.getUsername());
            List<Category> cats = categoryService.findByAnniv(ar.getData(),sessionUser.getUsername()).getData();

            for(Category cat: cats){

                childrenArrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("id", getTreeNodeId(cat))
                                .add("text", cat.getCat())
                                .add("leaf", true)
                );

            }

        }

        builder.add("children", childrenArrayBuilder);
        return toJsonString(builder.build());

    }

    private String getTreeNodeId(EntityItem obj) {

        String id = null;

        if(obj instanceof Corporate){
            id = "S_" + obj.getId();
        } else if (obj instanceof CorpAnniv){
            id = "A_" + obj.getId();
        } else if (obj instanceof Category){
            id = "C_" + obj.getId();
        }

        return id;
    }


//    @RequestMapping(value = "/{pageNumber}",method = RequestMethod.GET)
//    public String getCorporatePage(@PathVariable Integer pageNumber, Model model){
//
//        Page<Corporate> page=corporateService.getCorporates(pageNumber);
//        int current = page.getNumber()+1;
//        int begin = Math.max(1, current - 2);
//        int end = Math.min(begin + 2, page.getTotalPages());
//
//
//        model.addAttribute("corpPage",page);
//        model.addAttribute("corpList", page.getContent());
//        model.addAttribute("beginIndex",begin);
//        model.addAttribute("endIndex",end);
//        model.addAttribute("currentIndex",current);
//        return "corporates";
//
//    }

//    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
//    public String editCorporate(@PathVariable Integer id, Model model){
//        Corporate corporate=corporateService.search(id);
//        model.addAttribute("corporate", corporate);
//        return "corporate";
//    }
//
//    @RequestMapping(value="/add")
//    public String showRegistration(){
//        return "corp-register";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String doRegistration(@ModelAttribute("corporate") Corporate corporate, BindingResult result /*, RedirectAttributes redirectAttributes*/){
//
//        if(result.hasErrors()){
//            return "add";
//        }
//
//        corporateService.save(corporate);
//        //redirectAttributes.addFlashAttribute("success",true);
//        return "redirect:add?success=true";
//    }


    @RequestMapping(value = "/anniv/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAllAnnivs(
            @RequestParam(value = "idCorporate", required = true) Integer idCorporate,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<List<CorpAnniv>> ar = corpAnnivService.findByCorporate(corporateService.find(idCorporate,sessionUser.getUsername()).getData(),sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/anniv/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String storeAnniv(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        JsonObject jsonObj = parseJsonObject(jsonData);

        String startDateVal = jsonObj.getString("startDate");
        String endDateVal = jsonObj.getString("endDate");
        String renewalDateVal = jsonObj.getString("renewalDate");

        Result<CorpAnniv> ar = corpAnnivService.store(
                getIntegerValue(jsonObj.get("idCorporate")),
                getIntegerValue(jsonObj.get("idCorpAnniv")),
                getIntegerValue(jsonObj.get("idIntermediary")),
                getIntegerValue(jsonObj.get("anniv")),
                LocalDate.parse(startDateVal, DATE_FORMAT_yyyyMMdd),
                LocalDate.parse(endDateVal, DATE_FORMAT_yyyyMMdd),
                LocalDate.parse(renewalDateVal, DATE_FORMAT_yyyyMMdd),
                sessionUser.getUsername()
        );

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value="/anniv/remove", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String removeAnniv(
            @RequestParam(value = "idCorpAnniv", required = true) Integer idCorpAnniv,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<CorpAnniv> ar = corpAnnivService.remove(idCorpAnniv,sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/contactinfo/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findContact(
            @RequestParam(value ="idCorporate", required = true) Integer idCorporate,
            HttpServletRequest request ){

        User sessionUser = getSessionUser(request);

        Result<List<ContactInfo>> ar = contactInfoService.findAll(idCorporate, sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }
    }

    @RequestMapping(value = "/contactinfo/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String storeContact(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        JsonObject jsonObj = parseJsonObject(jsonData);

        Result<ContactInfo> ar = contactInfoService.store(
                getIntegerValue(jsonObj.get("idContactInfo")),
                getIntegerValue(jsonObj.get("idCorporate")),
                jsonObj.getString("firstName"),
                jsonObj.getString("surname"),
                jsonObj.getString("jobTitle"),
                jsonObj.getString("email"),
                jsonObj.getString("tel"),
                sessionUser.getUsername());


        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/contactinfo/remove", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String removeContact(
            @RequestParam(value = "idContactInfo") Integer idContactInfo,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<ContactInfo> ar = contactInfoService.remove(idContactInfo, sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }


}
