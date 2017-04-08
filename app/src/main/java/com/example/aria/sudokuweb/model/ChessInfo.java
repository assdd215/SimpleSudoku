package com.example.aria.sudokuweb.model;

import java.io.Serializable;

/**
 * Created by Aria on 2016/12/3.
 */

public class ChessInfo implements Serializable{
    private String title;
    private int id;
    private String content;

    public ChessInfo(){
        title = "LEVEL - 01";
        id = 1;
    }

    public ChessInfo(int id,String title,String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
