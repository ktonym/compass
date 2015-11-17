package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.PremiumInvoiceItem;
import ke.co.turbosoft.compass.entity.PremiumRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 17/11/2015.
 */
public interface PremiumInvoiceItemRepo extends JpaRepository<PremiumInvoiceItem,Integer> {

    List<PremiumInvoiceItem> findByPremiumRate(PremiumRate premiumRate);
}
