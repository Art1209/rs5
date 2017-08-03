package rs5.persistence.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Fetch;
import rs5.persistence.serializeTools.RolesListSerializeModel;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.FetchMode.SELECT;

@XmlRootElement
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", length = 6, nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @JsonSerialize(using= RolesListSerializeModel.class)
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> rolesList=new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Role> getRolesList() {

        return rolesList;
    }
    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getName() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User)obj;
        return this.getId()==other.getId();
    }
    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (int)(getId()%Integer.MAX_VALUE);
        return result;
    }
}
