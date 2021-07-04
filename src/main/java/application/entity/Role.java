package application.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.name = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        System.out.println("getAuthority запустился");
        return getName();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
