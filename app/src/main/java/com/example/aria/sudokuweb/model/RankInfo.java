package com.example.aria.sudokuweb.model;

import java.io.Serializable;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankInfo implements Serializable{
    private String title;
    private String username;
    private int usetime;
    private int isweb;

    public RankInfo(){}

    public RankInfo(String title,String username,int usetime){
        this.title = title;
        this.username = username;
        this.usetime = usetime;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsetime(int usetime) {
        this.usetime = usetime;
    }

    public int getUsetime() {
        return usetime;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public void setIsweb(int isweb) {
        this.isweb = isweb;
    }

    public int getIsweb() {
        return isweb;
    }
}
