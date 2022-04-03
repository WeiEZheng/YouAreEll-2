package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {
    private String name = "";
    private String github = "";
    @JsonProperty("userid")
    private String userid = "";

    public Id(){}

    public Id(String name, String github) {
        this("", name, github);
    }

    public Id (String userid, String name, String github) {
        this.userid = userid;
        this.name=name;
        this.github=github;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String uid) {
        this.userid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}