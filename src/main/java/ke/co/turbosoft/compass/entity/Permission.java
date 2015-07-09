package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ktonym on 6/25/15.
 */
@Entity @IdClass(PermissionId.class)
public class Permission extends AbstractEntity implements EntityItem<PermissionId> {

    @ManyToOne
    @JoinColumn(name="idUserGroup")
    private UserGroup userGroup;
    @ManyToOne
    @JoinColumn(name="idMenu")
    private Menu menu;

    public Permission() {
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public PermissionId getId() {
        return new PermissionId(menu,userGroup);
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        userGroup.addJson(builder);
        menu.addJson(builder);
    }
}
