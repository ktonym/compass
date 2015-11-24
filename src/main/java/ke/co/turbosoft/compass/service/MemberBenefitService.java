package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.repo.CorpAnnivRepo;
import ke.co.turbosoft.compass.repo.CorporateRepo;
import ke.co.turbosoft.compass.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by akipkoech on 23/11/2015.
 */
public interface MemberBenefitService {

    Result<String> execute(Integer idCorporate, String actionUsername);

}
