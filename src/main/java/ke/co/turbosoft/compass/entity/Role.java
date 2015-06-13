package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Role extends AbstractEntity implements EntityItem<String>{

    @Id
	private String roleName;
    private String description;
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    public Role() {
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return roleName;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("roleName",roleName).add("description",description);

    }
}
