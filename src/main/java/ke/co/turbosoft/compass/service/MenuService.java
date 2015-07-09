package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.Menu;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;

/**
 * Created by ktonym on 6/27/15.
 */
public interface MenuService {

    public Result<List<Menu>> findAll(String actionUsername);

}
