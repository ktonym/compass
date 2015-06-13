package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Role;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserRole;
import ke.co.turbosoft.compass.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 3/6/15.
 */
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findAll();

    List<UserRole> findByUser(User user);

    List<UserRole> findByRole(Role role);

//    List<RoleType> findByUser(User user);

    long countByUser(User user);

}
