package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpAnnivSuspension;
import ke.co.turbosoft.compass.vo.Result;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by akipkoech on 31/10/15.
 */
public interface CorpAnnivSuspService  {

    Result<CorpAnnivSuspension> store(Integer idCorpAnnivSusp,
                                      Integer idCorpAnniv,
                                      LocalDate start,
                                      LocalDate end,
                                      String reason,
                                      String actionUsername);

    Result<CorpAnnivSuspension> remove(Integer idCorpAnnivSusp, String actionUsername);

    Result<CorpAnnivSuspension> find(Integer idCorpAnnivSusp, String actionUsername);

    Result<List<CorpAnnivSuspension>> findAllInAnniv(Integer idCorpAnniv, String actionUsername);

    Result<List<CorpAnnivSuspension>> findAll(Integer idCorporate, String actionUsername);

}
