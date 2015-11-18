package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.vo.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface CorporateService {

//    Consider returning a Result<Page<Corporate>> object;
//    Page<Corporate> getCorporates(Integer pageNumber);

/*
    void renew(Corporate corporate);
    List<Corporate> listActive();
    List<Corporate> listInactive();
    List<Corporate> listJoinedAfter(LocalDate localDate);
    List<Corporate> listJoinedBefore(LocalDate localDate);
    List<Principal> listPrincipals(Corporate corporate);
    List<CorpAnniv> listAnniversaries(Corporate corporate);
    List<Member> listMembers(Corporate corporate);
    List<Member> listActiveMembers(Corporate corporate);
    List<Category> listCategories(Corporate corporate);
*/

    Result<Corporate> store(
            Integer idCorporate,
            String corporateName,
            String abbreviation,
            String tel,
            String email,
            String postalAddress,
            LocalDate joined,
            LocalDateTime lastUpdate,
            String actionUsername
    );

    Result<List<Corporate>> store(List<Map<String,Object>> corporateMap, String actionUsername);

    Result<Corporate> remove(Integer idCorporate, String actionUsername);

    Result<Corporate> find(Integer idCorporate, String actionUsername);

    Result<List<Corporate>> findAll(String actionUsername);

    Result<List<Corporate>> findModifiedAfter(LocalDateTime time, String actionUsername);

    Result<List<Corporate>> findAddedAfter(LocalDate time, String actionUsername);

    Result<List<Corporate>> findAddedBefore(LocalDate time, String actionUsername);

}
