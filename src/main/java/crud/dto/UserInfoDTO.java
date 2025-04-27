package crud.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserInfoDTO {

    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Patronymic should not be empty")
    private String patronymic;

    @NotEmpty(message = "Birthday should not be empty")
    private String birthday;

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
}