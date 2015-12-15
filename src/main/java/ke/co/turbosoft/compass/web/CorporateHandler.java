package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.service.*;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ke.co.turbosoft.compass.web.SecurityHelper.getSessionUser;

/**
 * Created by akipkoech on 12/10/14.
 */
@Controller
//@RequestMapping("/uw")
public class CorporateHandler extends AbstractHandler{

    @Autowired
    private CorpAnnivSuspService annivSuspService;

    @Autowired
    private CorporateService corporateService;

    @Autowired
    private CorpAnnivService corpAnnivService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContactInfoService contactInfoService;

    @Autowired
    private GroupRateService groupRateService;

    static final DateTimeFormatter DATE_FORMAT_yyyyMMdd =  DateTimeFormatter.ofPattern("yyyyMMdd");
    static final DateTimeFormatter DATE_FORMAT_yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.registerCustomEditor(LocalDate.class,new LocalDateEditor(DATE_FORMAT_yyyyMMdd,true));
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor(DATE_FORMAT_yyyyMMddHHmm,true));

    }

    @RequestMapping(value="/corporate/findAll",method = RequestMethod.GET, produces={"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<List<Corporate>> ar = corporateService.findAll(sessionUser.getUsername());

        if(ar.isSuccess()){

            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("success", true);

            JsonArrayBuilder corpArrayBuilder = Json.createArrayBuilder();

            for (Corporate corporate: ar.getData()){

                Result<List<CorpAnniv>> annivsAr = corpAnnivService.findByCorporate(corporate,sessionUser.getUsername());
                JsonArrayBuilder annivsArrayBuilder = Json.createArrayBuilder();

                if(annivsAr.isSuccess()){
                       for(CorpAnniv corpAnniv: annivsAr.getData()){
                           annivsArrayBuilder.add(
                                 Json.createObjectBuilder()
                                       .add("idCorpAnniv", corpAnniv.getId())
                                       .add("anniv", corpAnniv.getAnniv())
                                       .add("inception", corpAnniv.getInception().format(DATE_FORMAT_yyyyMMdd))
                                       .add("expiry", corpAnniv.getExpiry().format(DATE_FORMAT_yyyyMMdd))
                                       .add("renewal", corpAnniv.getRenewalDate().format(DATE_FORMAT_yyyyMMdd))
                                       .add("lastUpdate", corpAnniv.getLastUpdate().format(DATE_FORMAT_yyyyMMddHHmm))
                           );
                       }
                    corpArrayBuilder.add(Json.createObjectBuilder().add("annivs", annivsArrayBuilder));
                }

                Result<List<ContactInfo>> contactsAr = contactInfoService.findAll(corporate.getId(),sessionUser.getUsername());
                JsonArrayBuilder contactsArrayBuilder = Json.createArrayBuilder();
                if(contactsAr.isSuccess()){
                    for(ContactInfo contactInfo: contactsAr.getData()){
                        contactsArrayBuilder.add(
                                Json.createObjectBuilder()
                                        .add("idContactInfo", contactInfo.getId())
                                        .add("firstName", contactInfo.getFirstName())
                                        .add("surname", contactInfo.getSurname())
                                        .add("jobTitle", contactInfo.getJobTitle())
                                        .add("tel", contactInfo.getTel())
                                        .add("email", contactInfo.getEmail())
                        );
                    }
                    corpArrayBuilder.add(Json.createObjectBuilder().add("contacts", contactsArrayBuilder));
                }

                corpArrayBuilder.add(Json.createObjectBuilder()
                        .add("idCorporate", corporate.getId())
                        .add("corporateName", corporate.getCorporateName())
                        .add("abbreviation", corporate.getAbbreviation())
                        .add("email", corporate.getEmail())
                        .add("tel", corporate.getTel())
                        .add("postalAddress", corporate.getPostalAddress())
                        .add("joined", corporate.getJoined().format(DATE_FORMAT_yyyyMMdd)));

            }

            builder.add("data", corpArrayBuilder);
            return toJsonString(builder.build());
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

        Result<Corporate> ar = corporateService.find(idCorporate, sessionUser.getUsername());

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

        try{
            JsonObject jsonObj = parseJsonObject(jsonData);

            String joinDateVal = jsonObj.getString("joined");
            String lastUpdateVal = jsonObj.getString("lastUpdate");
            Integer idCorp = Integer.valueOf(jsonObj.getString("idCorporate"));
            Result<Corporate> ar = corporateService.store(
                    idCorp,
                    jsonObj.getString("corporateName"),
                    jsonObj.getString("abbreviation"),
                    jsonObj.getString("tel"),
                    jsonObj.getString("email"),
                    jsonObj.getString("postalAddress"),
                    LocalDate.parse(joinDateVal, DATE_FORMAT_yyyyMMdd),
                    LocalDateTime.parse(lastUpdateVal,DATE_FORMAT_yyyyMMddHHmm),
                    sessionUser.getUsername());

            if(ar.isSuccess()){
                return getJsonSuccessData(ar.getData());
            } else {
                return getJsonErrorMsg(ar.getMsg());
            }
        } catch (JsonException je){

            JsonArray jsonArray = parseJsonArray(jsonData);

            List<Map<String,Object>> mapList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++){

                JsonObject jsonObj = jsonArray.getJsonObject(i);
                Map map = new HashMap<>();

                //Integer idCorpStr = jsonObj.getInt("idCorporate");
                //long temp = (long) jsonObj.get("idCorporate");
                Integer idCorpInt = ((JsonNumber) jsonObj.get("idCorporate")).intValue();
                map.put("idCorporate", idCorpInt);//jsonObj.getInt("idCorporate"));
                map.put("corporateName", jsonObj.getString("corporateName"));
                map.put("abbreviation", jsonObj.getString("abbreviation"));
                map.put("tel", jsonObj.getString("tel"));
                map.put("email", jsonObj.getString("email"));
                map.put("postalAddress", jsonObj.getString("postalAddress"));
                map.put("joined", LocalDate.parse(jsonObj.getString("joined"), DATE_FORMAT_yyyyMMdd));
                map.put("lastUpdate", LocalDateTime.now());//.format(DATE_FORMAT_yyyyMMddHHmm)); //LocalDateTime.parse(jsonObj.getString("lastUpdate"), DATE_FORMAT_yyyyMMddHHmm));

                mapList.add(map);

            }

            Result<List<Corporate>> ar = corporateService.store(mapList,sessionUser.getUsername());
            if(ar.isSuccess()){
                return getJsonSuccessData(ar.getData());
            } else {
                return getJsonErrorMsg(ar.getMsg());
            }

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

        String inceptionVal = jsonObj.getString("inception");
        String expiryVal = jsonObj.getString("expiry");
        String renewalDateVal = jsonObj.getString("renewalDate");

        Result<CorpAnniv> ar = corpAnnivService.store(
                getIntegerValue(jsonObj.get("idCorporate")),
                getIntegerValue(jsonObj.get("idCorpAnniv")),
                getIntegerValue(jsonObj.get("idIntermediary")),
                getIntegerValue(jsonObj.get("anniv")),
                LocalDate.parse(inceptionVal, DATE_FORMAT_yyyyMMdd),
                LocalDate.parse(expiryVal, DATE_FORMAT_yyyyMMdd),
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

        Result<CorpAnniv> ar = corpAnnivService.remove(idCorpAnniv, sessionUser.getUsername());

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
            @RequestParam(value = "idContactInfo", required = true) Integer idContactInfo,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        Result<ContactInfo> ar = contactInfoService.remove(idContactInfo, sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/annivsusp/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String storeSuspension(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);
        JsonObject jsonObj = parseJsonObject(jsonData);
        String startDateVal = jsonObj.getString("startDate");
        String endDateVal = jsonObj.getString("endDate");
        Result<CorpAnnivSuspension> ar = annivSuspService.store(
                getIntegerValue(jsonObj.get("idAnnivSusp")),
                getIntegerValue(jsonObj.get("idCorpAnniv")),
                LocalDate.parse(startDateVal, DATE_FORMAT_yyyyMMdd),
                LocalDate.parse(endDateVal, DATE_FORMAT_yyyyMMdd),
                jsonObj.getString("reason"),
                sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/annivsusp/findAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public String findAllSuspensions(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);
        JsonObject jsonObj = parseJsonObject(jsonData);
        Result<List<CorpAnnivSuspension>> ar = annivSuspService.findAllInAnniv(
                getIntegerValue(jsonObj.get("idCorpAnniv")),
                sessionUser.getUsername());

        if(ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

    @RequestMapping(value = "/grouprate/store", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String storeGroupRates(
            @RequestParam(value = "data", required = true) String jsonData,
            HttpServletRequest request){

        User sessionUser = getSessionUser(request);

        try {

            JsonObject jsonObj = parseJsonObject(jsonData);
            String lastUpdateVal = jsonObj.getString("lastUpdate");
            Result<GroupRate> ar = groupRateService.store(
                    getIntegerValue(jsonObj.get("idPremiumRate")),
                    jsonObj.getString("premiumType"),
                    getIntegerValue(jsonObj.get("idCorporate")),
                    getIntegerValue(jsonObj.get("benefitCode")),
                    getBigDecimalValue(jsonObj.get("upperLimit")),
                    getBigDecimalValue(jsonObj.get("premium")),
                    jsonObj.getString("familySize"),
                    LocalDateTime.parse(lastUpdateVal, DATE_FORMAT_yyyyMMddHHmm),
                    sessionUser.getUsername());


            if (ar.isSuccess()) {
                return getJsonSuccessData(ar.getData());
            } else {
                return getJsonErrorMsg(ar.getMsg());
            }

        } catch (JsonException je){

            JsonArray jsonArray = parseJsonArray(jsonData);

            List<Map<String,Object>> mapList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++){

                JsonObject jsonObj = jsonArray.getJsonObject(i);
                Map map = new HashMap<>();

                map.put("idPremiumRate", getIntegerValue(jsonObj.get("idPremiumRate")));
                map.put("idCorporate", getIntegerValue(jsonObj.get("idCorporate")));
                map.put("familySize", jsonObj.getString("familySize"));
                map.put("premiumType", jsonObj.getString("premiumType"));
                map.put("benefitCode", getIntegerValue(jsonObj.get("benefitCode")));
                map.put("upperLimit", getBigDecimalValue(jsonObj.get("upperLimit")));
                map.put("premium", getBigDecimalValue(jsonObj.get("premium")));

                mapList.add(map);

            }

            Result<List<GroupRate>> ar = groupRateService.store(mapList,sessionUser.getUsername());

            if(ar.isSuccess()){
                return getJsonSuccessData(ar.getData());
            } else {
                return getJsonErrorMsg(ar.getMsg());
            }

        }

    }


    @RequestMapping(value = "/grouprate/findAll", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public String findAllGroupRates(@RequestParam (value = "data", required = true) String jsonData,
                                    HttpServletRequest request){

        User sessionUser = getSessionUser(request);
        JsonObject jsonObj = parseJsonObject(jsonData);
        Result<List<PremiumRate>> ar = groupRateService.findByCorporate(
                getIntegerValue(jsonObj.get("idCorporate")),
                sessionUser.getUsername()
        );

        if (ar.isSuccess()){
            return getJsonSuccessData(ar.getData());
        } else {
            return getJsonErrorMsg(ar.getMsg());
        }

    }

}
