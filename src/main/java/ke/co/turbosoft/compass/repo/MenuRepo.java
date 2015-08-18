package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
public interface MenuRepo extends JpaRepository<Menu,Integer> {

    @Query("SELECT m FROM Menu m WHERE m.idMenu IN " +
            "(SELECT p.menu FROM Permission p WHERE p.userGroup = " +
            "(SELECT u.userGroup FROM User u WHERE u.username = :username))")
    List<Menu> findMenusFromUser(@Param("username") String username);

    @Query("SELECT m FROM Menu m WHERE m.idMenu IN " +
            "(SELECT p.menu FROM Permission p WHERE p.userGroup = " +
            "(SELECT u.userGroup FROM User u WHERE u.username = :username)) AND m.parentMenu IS NULL")
    List<Menu> findModulesFromUser(@Param("username") String username);

    // TODO get the difference between Modules loading and menus loading from Loiane


}
