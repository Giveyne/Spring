package ru.igor.system.model;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

@Entity
@Table(name = "user_base")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 11)
    private Long id;

    @Column(name = "name", length = 16)
    @Size(min=3, max=16,
            message="Username must be between 3 and 16 characters long.")
    private String name;

    @Column(name = "password", length = 128)
    @NotBlank(message="Street is required")
    @Size(min=8, max=32, message="Username must be between 3 and 16 characters long.")
    @Pattern(regexp="^(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message="Password must contain min 8 character upper and lower case letters and digit")
    private String password;

    public User() { }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
