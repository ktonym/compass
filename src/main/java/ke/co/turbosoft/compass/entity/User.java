package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User extends AbstractEntity implements EntityItem<String> {

    @Id
    private String username;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]*(?:[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
	private String email;
	private String password;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "idGroup")
    private UserGroup userGroup;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
         builder.add("username",username)
                 .add("email",email)
                 .add("firstName", firstName)
                 .add("lastName",lastName);

        if(getUserGroup()!=null){
            userGroup.addJson(builder);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
