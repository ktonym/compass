package ke.co.turbosoft.compass.repo;

import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
public interface UserRepo extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username, String password);
    User findByEmail(String email);
    List<User> findByUserGroup(UserGroup userGroup);
}
