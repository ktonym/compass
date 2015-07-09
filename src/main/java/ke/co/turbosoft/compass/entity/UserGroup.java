package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by ktonym on 6/25/15.
 */
public class UserGroup extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idGroup;
    private String groupName;
    @OneToMany(mappedBy = "userGroup")
    private List<Permission> permissions;
    @OneToMany(mappedBy = "userGroup")
    private List<User> users;

    public UserGroup() {
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public Integer getId() {
        return idGroup;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idGroup", idGroup)
                .add("groupName", groupName);
    }
}

