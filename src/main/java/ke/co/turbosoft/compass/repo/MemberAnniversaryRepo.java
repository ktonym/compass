package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.CorpAnniv;
import ke.co.turbosoft.compass.entity.Member;
import ke.co.turbosoft.compass.entity.MemberAnnivId;
import ke.co.turbosoft.compass.entity.MemberAnniversary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akipkoech on 24/11/2015.
 */
public interface MemberAnniversaryRepo extends JpaRepository<MemberAnniversary, MemberAnnivId> {

    MemberAnniversary findByCorpAnnivAndMember(CorpAnniv corpAnniv, Member member);

}
