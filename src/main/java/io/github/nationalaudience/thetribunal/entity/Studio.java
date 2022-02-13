package io.github.nationalaudience.thetribunal.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int employees;

    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 140)
    private String location;

    @ManyToMany(mappedBy = "studios", cascade = CascadeType.ALL)
    private List<Game> games;

    @ManyToMany(mappedBy = "studiosFollow")
    private List<User> studioFollowedByUsers;

    public Studio() {}

    public Studio(String name, String description, String location, int employees, List<Game> games, List<User> followedByUsers) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.employees = employees;
        this.games = games;
        this.studioFollowedByUsers = followedByUsers;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public long getId() {
        return id;
    }

    public List<User> getStudioFollowedByUsers() {
        return studioFollowedByUsers;
    }

    public void setStudioFollowedByUsers(List<User> followedByUsers) {
        this.studioFollowedByUsers = followedByUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return id == studio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", employees=" + employees +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", games=" + games +
                '}';
    }
}
