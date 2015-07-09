package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.PermissionId;
import ke.co.turbosoft.compass.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
public interface PermissionRepo extends JpaRepository<Permission, PermissionId> {

    List<Permission>  findByUserGroup(UserGroup userGroup);
    List<Permission>  findByMenu(Menu menu);

}
