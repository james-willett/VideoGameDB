package com.techietester.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class VideoGame {

    private int id;
    private String name;
    private Date releaseDate;
    private int reviewScore;
    private String category;
    private String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    @XmlAttribute
    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    @XmlAttribute
    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }


}
