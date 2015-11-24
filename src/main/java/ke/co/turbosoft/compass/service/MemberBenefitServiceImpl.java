package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.*;
import ke.co.turbosoft.compass.repo.*;
import ke.co.turbosoft.compass.vo.Result;
import ke.co.turbosoft.compass.vo.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by akipkoech on 23/11/2015.
 */
@Service("memberBenefitService")
public class MemberBenefitServiceImpl extends AbstractService implements MemberBenefitService {

    @Autowired
    protected CorporateRepo corporateRepo;

    @Autowired
    protected CorpAnnivRepo corpAnnivRepo;

    @Autowired
    protected CorpAnnivSuspensionRepo suspensionRepo;

    @Autowired
    protected CategoryRepo categoryRepo;

    @Autowired
    protected MemberRepo memberRepo;

    @Autowired
    protected CorpMemberBenefitRepo memberBenefitRepo;

    @Autowired
    protected MemberAnniversaryRepo memAnnivRepo;

    public static Predicate<CorpAnnivSuspension> isActive() {
        return p -> (p.getStartDate().isBefore(LocalDate.now()) || p.getStartDate().isEqual(LocalDate.now()))
                && (p.getEndDate().isEqual(LocalDate.now()) || p.getEndDate().isAfter(LocalDate.now()));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Result<String> execute(Integer idCorporate, String actionUsername) {

        Corporate corporate = corporateRepo.findOne(idCorporate);

        List<CorpAnniv> annivList = corpAnnivRepo.findByCorporate(corporate);

        CorpAnniv latestAnniv = annivList.stream()
                .max(Comparator.comparing(ca -> ca.getAnniv()))
                .get();

        if(latestAnniv.getExpiry().isBefore(LocalDate.now())){
            return ResultFactory.getFailResult("No anniversary found to satisfy this request.");
        }

        List<CorpAnnivSuspension> suspensions = suspensionRepo.findByCorpAnniv(latestAnniv);
        if(suspensions.stream().anyMatch(isActive())){
            return ResultFactory.getFailResult("There is an active anniversary suspension.");
        }

        List<Category> categories = categoryRepo.findByAnniv(latestAnniv);

        if(categories.isEmpty()){
            return ResultFactory.getFailResult("No categories have been defined for the active anniversary.");
        }

//        List<CorpBenefit> benefits = categories.stream().forEach(Category::getCorpBenefits);

        for(Category category: categories){

            List<CorpBenefit> benefits = category.getCorpBenefits();

//            List<CategoryPrincipal> catPrincipals = category.getCategoryPrincipal();
            List<Member> memberList = memberRepo.findByPrincipal_CategoryPrincipal_Category(category);

            for(CorpBenefit benefit: benefits){

                MemberType memType = benefit.getMemberType();

                for (Member member : memberList){

                    MemberAnniversary memberAnniversary = memAnnivRepo.findByCorpAnnivAndMember(latestAnniv, member);

                    if(member.getMemberType().equals(memType)){ //Benefit is applicable to member
                        if (memberAnniversary!=null){ //member is active in current anniv
                            CorpMemberBenefit corpMemberBenefit = new CorpMemberBenefit();
                            corpMemberBenefit.setBenefit(benefit);
                            corpMemberBenefit.setMemberAnniv(memberAnniversary);
                            memberBenefitRepo.save(corpMemberBenefit);
                        }
                    }
                }

            }

        }

        return ResultFactory.getSuccessResult("Successfully saved member benefit(s).");

    }


}
