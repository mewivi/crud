package crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_details_table")
public class UserInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "patronymic")
    @NotEmpty(message = "Patronymic should not be empty")
    private String patronymic;

    @Column(name = "birthday")
    @NotEmpty(message = "Birthday should not be empty")
    private String birthday;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public @NotEmpty(message = "Surname should not be empty") String getSurname() {
        return surname;
    }

    public void setSurname(@NotEmpty(message = "Surname should not be empty") String surname) {
        this.surname = surname;
    }

    public @NotEmpty(message = "Name should not be empty") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Patronymic should not be empty") String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@NotEmpty(message = "Patronymic should not be empty") String patronymic) {
        this.patronymic = patronymic;
    }

    public @NotEmpty(message = "Birthday should not be empty") String getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotEmpty(message = "Birthday should not be empty") String birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo() {

    }
}