package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserRole;
import ke.co.turbosoft.compass.repo.PermissionRepo;
import ke.co.turbosoft.compass.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by akipkoech on 19/08/15.
 */
@Component
public class CompassUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PermissionRepo permissionRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userRepo.findOne(username);
        final Set<Permission> authorities = permissionRepo.findByUsername(username);
        //UserDetails user =

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),authorities);

    }
}
