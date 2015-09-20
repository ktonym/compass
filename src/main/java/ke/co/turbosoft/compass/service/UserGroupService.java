package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Permission;
import ke.co.turbosoft.compass.entity.UserGroup;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
public interface UserGroupService {
    public Result<List<UserGroup>> findAll(String actionUsername);
    public Result<UserGroup> find(String groupName, String actionUsername);
}
