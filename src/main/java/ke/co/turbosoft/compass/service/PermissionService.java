package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
public interface PermissionService {
    public Result<List<Permission>> findAll(String actionUsername);
    public Result<List<Permission>> findByUserGroup(Integer idUserGroup, String actionUsername);
    public Result<List<Permission>> findByMenu(Integer idMenu, String actionUsername);
    public Result<Permission> store(Integer idUserGroup, Integer idMenu, String actionUsername);
    public Result<Permission> remove(Integer idUserGroup, Integer idMenu, String actionUsername);
}
