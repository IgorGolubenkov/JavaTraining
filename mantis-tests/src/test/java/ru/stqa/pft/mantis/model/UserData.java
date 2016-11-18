package ru.stqa.pft.mantis.model;

public class UserData {

    private String username;
    private String password;
    private String email;

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }
    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }
    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
        if (password != null ? !password.equals(userData.password) : userData.password != null) return false;
        return email != null ? email.equals(userData.email) : userData.email == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
