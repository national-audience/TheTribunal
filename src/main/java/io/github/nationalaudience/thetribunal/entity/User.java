package io.github.nationalaudience.thetribunal.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", unique = true)
    private String username;
    private String passwordHash;

    private String name;
    private String description;


    private boolean darkMode;
    private String language;

    private boolean admin;

    @ManyToMany
    private List<Studio> studiosFollow;

    @ManyToMany
    private List<User> usersFollow;

    @ManyToMany(mappedBy = "usersFollow")
    private List<User> followedByUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    public User() {
    }

    public User(String username,
                String passwordHash,
                String name,
                String description,
                boolean darkMode,
                String language,
                boolean admin,
                List<Studio> studiosFollow,
                List<User> usersFollow,
                List<User> followedByUsers,
                List<Review> reviews) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
        this.description = description;
        this.darkMode = darkMode;
        this.language = language;
        this.admin = admin;
        this.studiosFollow = studiosFollow;
        this.usersFollow = usersFollow;
        this.followedByUsers = followedByUsers;
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Studio> getStudiosFollow() {
        return studiosFollow;
    }

    public void setStudiosFollow(List<Studio> studiosFollow) {
        this.studiosFollow = studiosFollow;
    }

    public List<User> getFollowedByUsers() {
        return followedByUsers;
    }

    public void setFollowedByUsers(List<User> followedByUsers) {
        this.followedByUsers = followedByUsers;
    }

    public List<User> getUsersFollow() {
        return usersFollow;
    }

    public void setUsersFollow(List<User> usersFollow) {
        this.usersFollow = usersFollow;
    }

    public long getId() {
        return id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", darkMode=" + darkMode +
                ", language='" + language + '\'' +
                ", admin=" + admin +
                '}';
    }
}
