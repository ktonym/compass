package ke.co.turbosoft.compass.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Generated;
import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ktonym on 6/25/15.
 */
@Entity //@IdClass(PermissionId.class)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"idUserGroup","idMenu"})})
public class Permission extends AbstractEntity implements EntityItem<Integer>, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPermission;

    private String name;

    @ManyToOne
    @JoinColumn(name="idUserGroup")
    private UserGroup userGroup;
    @ManyToOne
    @JoinColumn(name="idMenu")
    private Menu menu;

    public Permission() {
    }

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Integer getId() {
        return idPermission;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idPermission",idPermission)
               .add("name",name);
        userGroup.addJson(builder);
        menu.addJson(builder);
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
