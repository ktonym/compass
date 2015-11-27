package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.GroupRate;
import ke.co.turbosoft.compass.entity.PremiumRate;
import ke.co.turbosoft.compass.vo.Result;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by akipkoech on 17/11/2015.
 */
public interface GroupRateService {

    Result<GroupRate> store(
            Integer idPremiumRate,
            String premiumType,
            Integer idCorporate,
            Integer benefitCode,
            BigDecimal upperLimit,
            BigDecimal premium,
            String familySize,
            LocalDateTime lastUpdate,
            String actionUsername
    );

   // Result<List<GroupRate>> store(List<GroupRate> groupRates, String actionUsername);

    Result<List<GroupRate>> store(List<Map<String,Object>> groupRateMap, String actionUsername);

    Result<GroupRate> remove(Integer idPremiumRate, String actionUsername);

    Result<GroupRate> find(Integer idPremiumRate, String actionUsername);

    Result<List<GroupRate>> findAll(String actionUsername);

    Result<List<PremiumRate>> findByCorporate(Integer idCorporate, String actionUsername);



}
