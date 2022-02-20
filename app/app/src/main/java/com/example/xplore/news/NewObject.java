package com.example.xplore.news;

import java.util.ArrayList;
import java.util.List;

public class NewObject
{
    private String title;
    private String description;
    private String link;
    private String image;
    private String date;



    public NewObject(String title, String description, String link, String image) {
    }

    public NewObject() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
