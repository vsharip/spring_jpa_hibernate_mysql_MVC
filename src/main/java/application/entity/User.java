package application.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
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

    public User() {
    }

    public User(String name, String surname, String city, int age) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
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
}
