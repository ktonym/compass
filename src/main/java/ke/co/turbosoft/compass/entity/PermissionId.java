package ke.co.turbosoft.compass.entity;

import java.io.Serializable;

/**
 * Created by ktonym on 6/26/15.
 */
public class PermissionId implements Serializable{

    Menu menu;
    UserGroup userGroup;

    public PermissionId() {
    }

    public PermissionId(Menu menu, UserGroup userGroup) {
        this.menu = menu;
        this.userGroup = userGroup;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionId)) return false;

        PermissionId that = (PermissionId) o;

        if (!menu.equals(that.menu)) return false;
        if (!userGroup.equals(that.userGroup)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menu.hashCode();
        result = 31 * result + userGroup.hashCode();
        return result;
    }
}
