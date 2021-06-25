package application.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name", unique = true)
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 40, message = "Длина имени недопустимо")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, message = "Длина фамилии недопустимо")
    private String surname;

    @Column(name = "city")
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Введите название города")
    private String city;

    @Column(name = "age")
    @Min(value = 18L, message = "Соискатель должен быть старше 18 лет")
    @Max(value = 65L, message = "Соискатель должен быть младше 65 лет" )
    private int age;


    @Column(name = "password")
    @NotEmpty(message = "Поле обязательно для заполнения")
    @Size(min = 1, max = 20, message = "Введите от 1 до 20 символов")
    private String password;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, String city, int age, String password, boolean enabled, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = new HashSet<>();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password ;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
