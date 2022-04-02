package io.github.nationalaudience.thetribunal.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FeedResponse {
    private String username;
    private String game;
    private String comment;
    private int score;
    private Date date;


    public FeedResponse(@JsonProperty("username") String username,@JsonProperty("game") String game,
                        @JsonProperty("comment") String comment,
                        @JsonProperty("score") int score,@JsonProperty("date") Date date) {
        this.username = username;
        this.game = game;
        this.comment = comment;
        this.score = score;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}