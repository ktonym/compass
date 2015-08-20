package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by ktonym on 6/27/15.
 */
public interface PermissionRepo extends JpaRepository<Permission, Integer> {

    List<Permission>  findByUserGroup(UserGroup userGroup);
    List<Permission>  findByMenu(Menu menu);
    Permission findByUserGroupAndMenu(UserGroup usergroup,Menu menu);

    @Query("SELECT p FROM Permission p WHERE p.userGroup = " +
            "(SELECT u.userGroup FROM User u WHERE u.username = :username)")
    Set<Permission> findByUsername(@Param("username") String username);

}
