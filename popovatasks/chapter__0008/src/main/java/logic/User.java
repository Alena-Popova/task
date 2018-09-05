package logic;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private  int id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public User(int id, String name, String login, String email, Date createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "\n User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
