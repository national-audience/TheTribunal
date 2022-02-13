package io.github.nationalaudience.thetribunal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(Review.ReviewId.class)
public class Review {

    public static final class ReviewId implements Serializable {

        private long user;
        private long game;

        public ReviewId() {
            this(-1, -1);
        }

        public ReviewId(long user, long game) {
            this.user = user;
            this.game = game;
        }

        public long getUser() {
            return user;
        }

        public void setUser(long user) {
            this.user = user;
        }

        public long getGame() {
            return game;
        }

        public void setGame(long game) {
            this.game = game;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (ReviewId) obj;
            return Objects.equals(this.user, that.user) &&
                    Objects.equals(this.game, that.game);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, game);
        }

        @Override
        public String toString() {
            return "ReviewId[" +
                    "user=" + user + ", " +
                    "game=" + game + ']';
        }

    }

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Game game;

    private int score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Review() {

    }

    public Review(int score, String comment, Date date, User user, Game game) {
        this.score = score;
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.game = game;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(user, review.user) && Objects.equals(game, review.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, game);
    }

    @Override
    public String toString() {
        return "Review{" +
                "user=" + user +
                ", game=" + game +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
