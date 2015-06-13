package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.ContactInfo;
import ke.co.turbosoft.compass.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface ContactInfoRepo extends JpaRepository<ContactInfo,Integer> {

    List<ContactInfo> findByCorporate(Corporate corporate);
    ContactInfo findByEmail(String email);

}
