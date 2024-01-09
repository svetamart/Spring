package org.example;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private String tasksFileName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    public User() {

    }

    public User(int id, String username, String password, String tasksFileName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tasksFileName = tasksFileName;
    }

    public User(String username, String password, String tasksFileName) {
        this.username = username;
        this.password = password;
        this.tasksFileName = tasksFileName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTasksFileName() {
        return tasksFileName;
    }

}

