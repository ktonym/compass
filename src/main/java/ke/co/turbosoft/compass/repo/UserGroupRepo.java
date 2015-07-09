package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ktonym on 6/27/15.
 */
public interface UserGroupRepo extends JpaRepository<UserGroup, Integer> {

    UserGroup findByGroupName(String groupName);

}
