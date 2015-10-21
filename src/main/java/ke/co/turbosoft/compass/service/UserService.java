package ke.co.turbosoft.compass.service;

import ke.co.turbosoft.compass.entity.User;
import ke.co.turbosoft.compass.entity.UserRole;
import ke.co.turbosoft.compass.vo.Result;

import java.util.List;

public interface UserService {
	
	public Result<List<User>> findAll(String actionUsername);
	public Result<User> findByUsernameAndPassword(String username, String password);
	public Result<User> store(
            String username,
            String email,
            String firstName,
            String lastName,
            String actionUsername);
    public Result<User> remove(String username, String actionUsername);

    public Result<User> find(String username, String actionUsername);

    public Result<List<UserRole>> findUserRole(String username);
}
