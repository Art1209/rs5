package rs5.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs5.persistence.serializeTools.UsersListSerializeModel;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", length = 6, nullable = false)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @JsonSerialize(using= UsersListSerializeModel.class)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "role_ID", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "users_ID", nullable = false) })
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> user) {
        this.users = users;
    }

    public Long getId() {
        return roleId;
    }
    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.roleId = id;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        Role other = (Role)obj;
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
