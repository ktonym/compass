package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.CorpAnnivSuspension;
import ke.co.turbosoft.compass.entity.Corporate;
import ke.co.turbosoft.compass.repo.CorpAnnivRepo;
import ke.co.turbosoft.compass.repo.CorpAnnivSuspensionRepo;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by akipkoech on 23/11/2015.
 */
@Service("populateMemberBenefits")
public class PopulateMemberBenefitsImpl extends AbstractService implements PopulateMemberBenefits {

    @Autowired
    protected CorpAnnivRepo corpAnnivRepo;

    @Autowired
    protected CorpAnnivSuspensionRepo suspensionRepo;

//    public static Predicate<CorpAnnivSuspension> isActive() {
//        return p -> (p.getStartDate().isBefore(LocalDate.now()) || p.getStartDate().) && p.getGender().equalsIgnoreCase("F");
//    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<String> execute(Corporate coporate, String actionUsername) {

        List<CorpAnniv> annivList = corpAnnivRepo.findByCorporate(coporate);

        CorpAnniv latestAnniv = annivList.stream()
                .max(Comparator.comparing(corpAnniv -> corpAnniv.getAnniv()))
                .get();

        if(latestAnniv.getExpiry().isBefore(LocalDate.now())){
            ResultFactory.getSuccessResult("No anniversary found to satisfy this request.");
        }

//        List<CorpAnnivSuspension> suspensions = suspensionRepo.findByCorpAnniv(latestAnniv);
//         CorpAnnivSuspension activeSuspension = suspensions.stream()
//                 .anyMatch()

        return null;

    }


}
