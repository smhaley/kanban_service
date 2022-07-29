package com.home.kanban_service.users;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name = "UserItem_sequence",
            sequenceName = "UserItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UserItem_sequence"
    )
    private Long id;

    @NotNull(message="username is required")
    private String username;

    public User() {
    }

    public User(String userName) {
        this.username = userName;
    }

    public User(Long id, String userName) {
        this.id = id;
        this.username = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                '}';
    }
}
