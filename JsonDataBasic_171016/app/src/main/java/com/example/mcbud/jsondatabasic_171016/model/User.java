package com.example.mcbud.jsondatabasic_171016.model;

/**
 * Created by mcbud on 2017-10-16.
 */

public class User {
    // id, site_admin, avart_url만 사용
    int id;
    boolean site_admin;
    String avatar_url;

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getAvatar_url() {
        return avatar_url;
    }

    String login;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;



}
