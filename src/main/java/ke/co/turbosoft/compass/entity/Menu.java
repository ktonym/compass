package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by ktonym on 6/25/15.
 */
@Entity
public class Menu extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMenu;
    private String text;
    private String iconCls;
    private String className;
    @OneToMany(mappedBy = "parentMenu")
    private List<Menu> subMenu;
    @ManyToOne()//cascade = CascadeType.ALL)
    @JoinColumn(name = "idParentMenu")
    private Menu parentMenu;
    @OneToMany(mappedBy = "menu")
    private List<Permission> permissions;

    public Menu() {
    }

    @Override
    public Integer getId() {
        return idMenu;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("idMenu",idMenu)
                .add("text", text)
                .add("iconCls", iconCls)
                .add("className",className);
        if (parentMenu != null){
            parentMenu.addJson(builder);
        }



    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
