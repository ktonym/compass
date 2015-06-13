package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity //@IdClass(UserRoleId.class)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username","idRole"})})
public class UserRole extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUserRole;

    @ManyToOne
    @JoinColumn(name="username")
    private User user;

    @Column(name="idRole")
    private Role role;

    public UserRole() {
    }

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idUserRole",idUserRole);

        if (user!=null){
             user.addJson(builder);
        }
        if (role!=null){
             role.addJson(builder);
        }

    }

    @Override
    public Integer getId() {
        return idUserRole;
    }
}
